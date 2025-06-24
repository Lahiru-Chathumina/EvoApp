import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from '../service/auth-service/auth.service';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { YourChatBortService } from '../service/chat-boat/your-chat-bort.service';
import { FormsModule } from '@angular/forms';



@Component({
  selector: 'app-root',
  imports: [RouterOutlet,CommonModule,FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'EVO-Frontend-app';
  showWhatsAppBoat = false

  userMessage: string = '';
  botResponse: string = '';
  isLoading: boolean = false;
  messages: { from: 'bot' | 'user', text: string }[] = [];

   quickReplies: string[]= [

    "What documents are required?",
    "What documents are required?",
    "How do I book an appointment?",
    "What services do you offer?",
 
  ];

 
  constructor(private http:HttpClient,private yoourChatBortService: YourChatBortService) {

   }
  sendMassage() {
    if (!this.userMessage.trim()) return;

    if (!this.quickReplies.includes(this.userMessage)) {
      this.botResponse = 'Please select a question from the quick replies.';
      return;
    }

    this.isLoading = true;
    this.yoourChatBortService.sendMessage(this.userMessage).subscribe({
      next: (res) => {
        this.botResponse = res?.candidates?.[0]?.content?.parts?.[0]?.text || 'No response received.';
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Gemini Error:', err);
        this.botResponse = 'Failed to get response.';
        this.isLoading = false;
      }
    });

    this.userMessage = '';
  }

   handleQuickReply(reply: string): void {
    this.userMessage = reply;
    setTimeout(() => {
      const input = document.querySelector('.chat-input input') as HTMLElement;
      input?.focus();
    }, 0);
  }
}

   

  




  

