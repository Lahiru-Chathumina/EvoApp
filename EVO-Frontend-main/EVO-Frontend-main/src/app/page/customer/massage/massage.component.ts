import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit, ChangeDetectorRef, ElementRef, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Client, over } from 'stompjs';
import { catchError, of, retry, tap } from 'rxjs';

const BACKEND_BASE_URL = 'http://localhost:8080';

@Component({
  selector: 'app-customer-chat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './massage.component.html',
  styleUrls: ['./massage.component.css'],
  providers: [HttpClient]
})
export class MassageComponent implements OnInit, OnDestroy {
  @ViewChild('chatContainer') chatContainer!: ElementRef;

  readonly customerId = 201;

  supplierIds: string[] = [];
  selectedSupplierId: string | null = null;
  messages: any[] = [];
  messageText = '';

  loadingSuppliers = false;
  loadingMessages = false;
  loadError: string | null = null;
  messageLoadError: string | null = null;

  private stompClient: Client | null = null;
  connectionStatus: 'CONNECTED' | 'CONNECTING' | 'DISCONNECTED' = 'DISCONNECTED';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadSuppliers();
    this.connect();
  }

  ngOnDestroy(): void {
    this.disconnect();
  }

  private loadSuppliers(): void {
    this.loadingSuppliers = true;

    this.http.get<string[]>(`${BACKEND_BASE_URL}/system/message/customer-supplier/suppliersByCustomerId/${this.customerId}`)
      .pipe(
        retry(2),
        catchError(err => {
          this.loadError = err.message || 'Failed to load suppliers';
          return of([]);
        }),
        tap(() => this.loadingSuppliers = false)
      )
      .subscribe(suppliers => {
        this.supplierIds = suppliers;
        this.cdr.detectChanges();
      });
  }

  selectSupplier(supplierId: string): void {
    this.selectedSupplierId = supplierId;
    this.loadMessages();
    this.subscribeToMessages();
  }

  private loadMessages(): void {
    if (!this.selectedSupplierId) return;

    this.loadingMessages = true;
    this.http.get<any[]>(`${BACKEND_BASE_URL}/system/message/customer-supplier/chat/${this.customerId}/${this.selectedSupplierId}`)
      .pipe(
        catchError(err => {
          this.messageLoadError = err.message || 'Failed to load messages';
          return of([]);
        }),
        tap(() => this.loadingMessages = false)
      )
      .subscribe(messages => {
        this.messages = messages;
        this.scrollToBottom();
        this.cdr.detectChanges();
      });
  }

  connect(): void {
    this.connectionStatus = 'CONNECTING';
    const ws = new WebSocket('ws://localhost:8080/ws');
    

    this.stompClient = over(ws);

    this.stompClient.connect({}, () => {
      this.connectionStatus = 'CONNECTED';
      this.cdr.detectChanges();
      this.subscribeToMessages();
    }, (error) => {
      this.connectionStatus = 'DISCONNECTED';
      this.cdr.detectChanges();
      console.error('Connection error:', error);
    });
  }

  private disconnect(): void {
    if (this.stompClient?.connected) {
      this.stompClient.disconnect(() => {
        this.connectionStatus = 'DISCONNECTED';
        this.cdr.detectChanges();
      });
    }
  }

  private subscribeToMessages(): void {
    if (!this.selectedSupplierId || !this.stompClient?.connected) return;

    const topic = `/topic/messages/${this.customerId}/${this.selectedSupplierId}`;
    this.stompClient.subscribe(topic, (message) => {
      const newMessage = JSON.parse(message.body);
      this.messages = [...this.messages, newMessage];
      this.cdr.detectChanges(); // Ensure DOM is updated
      this.scrollToBottom(); // Scroll to the bottom
    });
  }

  sendMessage(): void {
    if (!this.messageText.trim() || !this.selectedSupplierId || !this.stompClient?.connected) return;

    const message = {
      customerId: this.customerId,
      supplierId: this.selectedSupplierId,
      content: this.messageText,
      sendTime: new Date().toISOString(),
      userType: 'CUSTOMER'
    };
    this.stompClient.send(
      `/app/chat/${this.customerId}/${this.selectedSupplierId}`,
      {},
      JSON.stringify(message)
    );

    this.messageText = '';
  }

  private scrollToBottom(): void {
    if (this.chatContainer && this.chatContainer.nativeElement) {
      setTimeout(() => {
        try {
          this.chatContainer.nativeElement.scrollTop = this.chatContainer.nativeElement.scrollHeight;
          console.log('Auto-scrolled to bottom:', this.chatContainer.nativeElement.scrollTop);
        } catch (error) {
          console.error('Error while auto-scrolling:', error);
        }
      }, 0);
    } else {
      console.warn('Chat container is not available for scrolling.');
    }
  }
}
