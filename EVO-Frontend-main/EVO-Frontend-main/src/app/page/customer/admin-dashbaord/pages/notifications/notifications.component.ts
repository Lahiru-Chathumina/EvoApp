import { Component, ViewChild, ElementRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-notifications',
  imports: [CommonModule,FormsModule],
  templateUrl: './notifications.component.html',
  styleUrl: './notifications.component.css'
})
export class NotificationsComponent {
  notifications: any[] = [];

  newNotification = {
    status: 'Delivering',
    description: '',
    trigger: '',
    date: ''
  };
  @ViewChild('createNotificationModal') createNotificationModal!: ElementRef;

  addNotification() {
    if (this.newNotification.description && this.newNotification.trigger) {
      this.notifications.push({ ...this.newNotification });
      this.resetForm();

      const modalElement = this.createNotificationModal.nativeElement;
      const modal = new bootstrap.Modal(modalElement);
      modal.hide();
    }
  }

  resetForm() {
    this.newNotification = {
      status: 'Delivering',
      description: '',
      trigger: '',
      date: this.getCurrentDateTime()
    };
  }

  getCurrentDateTime(): string {
    const now = new Date();
    return now.toISOString().slice(0, 16).replace('T', ' '); 
  }

  deleteNotification(index: number) {
    this.notifications.splice(index, 1);
  }

  updateNotification(notification: any) {
    console.log('Updating:', notification);
  }
}