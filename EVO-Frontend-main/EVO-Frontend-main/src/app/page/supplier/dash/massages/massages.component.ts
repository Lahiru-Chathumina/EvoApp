import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnDestroy, OnInit, ChangeDetectorRef, ViewChild, ElementRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Client, over } from 'stompjs';
import { catchError, of, retry, tap } from 'rxjs';

interface Message {
  content: string;
  sendTime: Date;
  sender: 'SUPPLIER' | 'ADMIN' | 'CUSTOMER' |'USER';
  adminId?: number;
  customerId?: number;
  supplierId: number;
}

interface IncomingMessage {
  content: string;
  sendTime: string;
  userType: 'SUPPLIER' | 'ADMIN' |'USER' | 'CUSTOMER';
  adminId: number;
  supplierId: number;
  customerId?: number;

}

type ChatType = 'ADMIN' | 'CUSTOMER';

@Component({
  selector: 'app-massages',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './massages.component.html',
  styleUrls: ['./massages.component.css'],
  providers: [DatePipe]
})
export class MassagesComponent implements OnInit, OnDestroy {
  @ViewChild('chatContainer') chatContainer!: ElementRef;

  connectionStatus: 'CONNECTED' | 'CONNECTING' | 'DISCONNECTED' = 'DISCONNECTED';
  messageText: string = '';
  private stompClient: Client | null = null;
  private webSocket: WebSocket | null = null;
  messages: Message[] = [];

  adminIds: number[] = [];
  selectedAdminId: number | null = null;
  customerIds: number[] = [];
  selectedCustomerId: number | null = null;
  supplierId: number = 101;
  activeChatType: ChatType = 'ADMIN';

  loadingAdmins = true;
  loadingCustomers = true;
  loadError: string | null = null;
  wsUrl = 'ws://localhost:8080/ws';
  reconnectAttempts = 0;
  loadingMessages = false;

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef,
    private datePipe: DatePipe
  ) {}

  ngOnInit() {
    this.loadAdminIds();
    this.loadCustomerIds();
    this.initializeWebSocket();
  }

  private initializeWebSocket() {
    this.connectionStatus = 'CONNECTING';
    this.webSocket = new WebSocket(this.wsUrl);
    this.stompClient = over(this.webSocket);

    this.stompClient.connect({},
        (frame) => this.onConnectSuccess(frame),
        (error) => this.onConnectError(error)
    );
  }

  private loadAdminIds() {
    this.http.get<number[]>(
     `http://localhost:8080/system/message/admin-supplier/adminsBySupplierId?supplierId=${this.supplierId}`
    ).pipe(
      retry(2),
      catchError(error => {
        this.loadError = error.message;
        this.loadingAdmins = false;
        return of([]);
      }),
      tap(() => this.loadingAdmins = false)
    ).subscribe(ids => {
      this.adminIds = ids;
      if (ids.length > 0) this.selectAdmin(ids[0]);
    });
  }

  private loadCustomerIds() {
    this.http.get<number[]>(
      `http://localhost:8080/system/message/customer-supplier/customersBySupplierId?supplierId=${this.supplierId}`
    ).pipe(
      retry(2),
      catchError(error => {
        this.loadError = error.message;
        this.loadingCustomers = false;
        return of([]);
      }),
      tap(() => this.loadingCustomers = false)
    ).subscribe(ids => {
      this.customerIds = ids;
      if (ids.length > 0) this.selectedCustomerId = ids[0];
    });
  }

  private loadMessages() {
    this.loadingMessages = true;
    if (this.activeChatType === 'ADMIN') {
      this.loadAdminMessages();
    } else {
      this.loadCustomerMessages();
    }
  }

  private loadAdminMessages() {
    if (!this.selectedAdminId) return;
    this.http.get<IncomingMessage[]>(
     `http://localhost:8080/system/message/admin-supplier/chat/${this.selectedAdminId}/${this.supplierId}`
    ).pipe(
      catchError(error => {
        console.error('Failed to load admin messages:', error);
        return of([]);
      })
    ).subscribe(messages => this.processMessages(messages));
  }

  private loadCustomerMessages() {
    if (!this.selectedCustomerId) return;
    this.http.get<IncomingMessage[]>(
      `http://localhost:8080/system/message/customer-supplier/chat/${this.selectedCustomerId}/${this.supplierId}`
    ).pipe(
      catchError(error => {
        console.error('Failed to load customer messages:', error);
        return of([]);
      })
    ).subscribe(messages => this.processMessages(messages));
  }

  private processMessages(messages: IncomingMessage[]) {
    messages.forEach(msg => {
      this.messages.push({
        content: msg.content,
        sendTime: new Date(msg.sendTime),
        sender: msg.userType,
        adminId: msg.adminId,
        customerId: msg.customerId,
        supplierId: msg.supplierId
      });
    });
    this.loadingMessages = false;
    this.cdr.detectChanges();
    this.scrollToBottom(); // Ensure scrolling happens after processing messages
  }

  selectAdmin(adminId: number) {
    this.selectedAdminId = adminId;
    this.activeChatType = 'ADMIN';
    this.loadMessages();
    this.updateWebSocketSubscription();
    this.scrollToBottom(); // Scroll to the bottom after selecting an admin
  }

  selectCustomer(customerId: number) {
    this.selectedCustomerId = customerId;
    this.activeChatType = 'CUSTOMER';
    this.loadMessages();
    this.updateWebSocketSubscription();
    this.scrollToBottom(); // Scroll to the bottom after selecting a customer
  }

  switchChatType(chatType: ChatType) {
    this.activeChatType = chatType;
    this.loadMessages();
    this.updateWebSocketSubscription();
  }

  private updateWebSocketSubscription() {
    if (!this.stompClient?.connected) return;

    if ((this.stompClient.subscriptions as any)?.['current_chat']) {
      this.stompClient.unsubscribe('current_chat');
    }

    const subscriptionPath = this.activeChatType === 'ADMIN'
    ? `/topic/chat/${this.supplierId}/${this.selectedAdminId}`
    : `/topic/messages/${this.selectedCustomerId}/${this.supplierId}`;
    this.stompClient.subscribe(
      subscriptionPath,
      (message) => this.handleIncomingMessage(JSON.parse(message.body)),
      { id: 'current_chat' }
    );

  }

  private onConnectSuccess(frame: any) {
    this.connectionStatus = 'CONNECTED';
    this.reconnectAttempts = 0;
    this.updateWebSocketSubscription();
    this.cdr.detectChanges();
  }

  private onConnectError(error: any) {
    console.error('Connection error:', error);
    this.connectionStatus = 'DISCONNECTED';
    this.reconnectAttempts++;
    const delay = Math.min(5000 * this.reconnectAttempts, 30000);
    setTimeout(() => this.connect(), delay);
    this.cdr.detectChanges();
  }

  connect() {
    this.initializeWebSocket();
  }

  private handleIncomingMessage(message: IncomingMessage) {
    const newMsg: Message = {
      content: message.content,
      sendTime: new Date(message.sendTime),
      sender: message.userType,
      adminId: message.adminId,
      customerId: message.customerId,
      supplierId: message.supplierId
    };
    this.messages.push(newMsg);
    this.cdr.detectChanges();
    this.scrollToBottom(); // Ensure scrolling happens after DOM updates
  }

  private scrollToBottom() {
    if (this.chatContainer) {
      setTimeout(() => {
        const container = this.chatContainer.nativeElement;
        container.scrollTop = container.scrollHeight;
      }, 0);
    }
  }

  sendMessage() {
    if (!this.messageText.trim() || !this.stompClient?.connected) return;

    const message = {
      content: this.messageText,
      supplierId: this.supplierId,
      userType: 'SUPPLIER',
      ...(this.activeChatType === 'ADMIN' ? { adminId: this.selectedAdminId } : { customerId: this.selectedCustomerId })
    };

    const destination = this.activeChatType === 'ADMIN'
      ? `/app/chat/admin-supplier/${this.supplierId}/${this.selectedAdminId}`
      : `/app/chat/${this.selectedCustomerId}/${this.supplierId}`;
    this.stompClient.send(destination, {}, JSON.stringify(message));

    // Do not push the message here; wait for it to arrive via WebSocket subscription

    this.messageText = '';
    this.cdr.detectChanges();
  }

  ngOnDestroy() {
    this.stompClient?.disconnect(() => console.log('Disconnected'));
    this.webSocket?.close();
  }
}
