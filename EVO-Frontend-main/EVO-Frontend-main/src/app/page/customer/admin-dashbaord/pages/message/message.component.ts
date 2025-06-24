// F:\PROJECT CLASS\front\EVO-Frontend\src\app\page\customer\admin-dashbaord\pages\message\message.component.ts

import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit, ChangeDetectorRef, ViewChild, ElementRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Client, over } from 'stompjs';
import { catchError, of, retry } from 'rxjs';

@Component({
  selector: 'app-message',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './message.component.html',
  styleUrl: './message.component.css'
})
export class MessageComponent implements OnInit, OnDestroy {
  @ViewChild('chatContainer') chatContainer!: ElementRef; // Reference to the chat container

  connectionStatus: 'CONNECTED' | 'CONNECTING' | 'DISCONNECTED' = 'DISCONNECTED';
  messageText: string = '';
  private stompClient: Client | null = null;
  private webSocket: WebSocket | null = null;
  messages: any[] = [];
  supplierIds: string[] = [];
  selectedSupplierId: string | null = null;
  adminId: number = 1;

  loadingSuppliers = true;
  loadingMessages = false;
  loadError: string | null = null;
  messageLoadError: string | null = null;
  wsUrl = 'ws://localhost:8080/ws';
  reconnectAttempts = 0;

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.loadSupplierIds();
    this.initializeWebSocket();
  }

  private initializeWebSocket() {
    this.connect();
  }

  private loadSupplierIds() {
    this.loadingSuppliers = true;
    this.http.get<string[]>(
      `http://localhost:8080/system/message/admin-supplier/suppliersByAdminId/${this.adminId}`
    ).pipe(
      retry(2),
      catchError(error => {
        this.loadError = error.message;
        this.loadingSuppliers = false;
        return of([]);
      })
    ).subscribe(ids => {
      this.supplierIds = ids;
      this.loadingSuppliers = false;
      this.cdr.detectChanges();
    });
  }

  private loadMessages(supplierId: string) {
    this.loadingMessages = true;
    this.messageLoadError = null;
    this.messages = [];
    this.cdr.detectChanges();

    this.http.get<any[]>(
      `http://localhost:8080/system/message/admin-supplier/chat/${this.adminId}/${supplierId}`
    ).pipe(
      catchError(error => {
        this.messageLoadError = error.message || 'Failed to load messages';
        this.loadingMessages = false;
        this.cdr.detectChanges();
        return of([]);
      })
    ).subscribe(messages => {
      this.messages = messages.map(msg => ({
        content: msg.content,
        timestamp: this.parseDate(msg.sendTime),
        sender: msg.userType === 'SUPPLIER' ? 'SUPPLIER' : 'ADMIN'
      }));
      this.loadingMessages = false;
      this.cdr.detectChanges();
      this.scrollToBottom(); // Scroll to the bottom after loading messages
    });
  }

  private handleIncomingMessage(message: any) {
    const isRelevantMessage =
      message.supplierId == this.selectedSupplierId &&
      message.adminId == this.adminId.toString();

    if (isRelevantMessage) {
      const newMsg = {
        content: message.content,
        timestamp: this.parseDate(message.sendTime),
        sender: message.userType === 'SUPPLIER' ? 'SUPPLIER' : 'ADMIN'
      };

      if (
        !this.messages.some(
          (m) =>
            m.content === newMsg.content &&
            m.timestamp.getTime() === newMsg.timestamp.getTime() &&
            m.sender === newMsg.sender
        )
      ) {
        this.messages = [...this.messages, newMsg];
        this.cdr.detectChanges();
        this.scrollToBottom(); // Scroll to the bottom when a new message arrives
      }
    }
  }

  sendMessage() {
    if (!this.selectedSupplierId || !this.messageText.trim() || !this.stompClient?.connected) {
      return;
    }

    const message = {
      adminId: this.adminId,
      supplierId: this.selectedSupplierId,
      content: this.messageText,
      sendTime: new Date().toISOString(),
      userType: 'ADMIN'
    };

    const destination = `/app/chat/admin-supplier/${this.selectedSupplierId}/${this.adminId}`;
    this.stompClient.send(destination, {}, JSON.stringify(message));
    this.messageText = '';
    this.cdr.detectChanges();
  }

  selectSupplier(supplierId: string) {
    this.selectedSupplierId = supplierId;
    this.messages = [];
    this.loadMessages(supplierId);
    this.updateWebSocketSubscription();
  }

  private scrollToBottom() {
    if (this.chatContainer) {
      setTimeout(() => {
        const container = this.chatContainer.nativeElement;
        container.scrollTop = container.scrollHeight;
      }, 100); // Slightly increased delay for DOM updates
    }
  }

  private updateWebSocketSubscription() {
    if (this.stompClient?.connected && this.selectedSupplierId) {
      const subscriptionPath = `/topic/chat/${this.selectedSupplierId}/${this.adminId}`;
      if ((this.stompClient as any).subscriptions['current_chat']) {
        this.stompClient.unsubscribe('current_chat');
      }
      this.stompClient.subscribe(
        subscriptionPath,
        (message) => this.handleIncomingMessage(JSON.parse(message.body)),
        { id: 'current_chat' }
      );
    }
  }

  connect() {
    this.connectionStatus = 'CONNECTING';
    this.cdr.detectChanges();

    this.webSocket = new WebSocket(this.wsUrl);
    this.stompClient = over(this.webSocket);

    this.stompClient.connect(
      {},
      (frame: any) => this.onConnectSuccess(frame),
      (error: any) => this.onConnectError(error)
    );
  }

  private onConnectSuccess(frame: any) {
    this.connectionStatus = 'CONNECTED';
    this.reconnectAttempts = 0;
    this.cdr.detectChanges();
  }

  private onConnectError(error: any) {
    this.connectionStatus = 'DISCONNECTED';
    this.reconnectAttempts++;
    this.cdr.detectChanges();

    const delay = Math.min(5000 * this.reconnectAttempts, 30000);
    setTimeout(() => this.connect(), delay);
  }

  private parseDate(dateString: string): Date {
    const date = new Date(dateString);
    return isNaN(date.getTime()) ? new Date() : date;
  }

  ngOnDestroy() {
    if (this.stompClient?.connected) {
      this.stompClient.disconnect(() => {});
    }
    if (this.webSocket) {
      this.webSocket.close();
    }
  }
}