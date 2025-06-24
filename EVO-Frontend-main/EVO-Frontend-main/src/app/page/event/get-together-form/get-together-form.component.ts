import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { event } from 'jquery';
import Swal from 'sweetalert2';
import { routes } from '../../../app.routes';
import { Router } from '@angular/router';


@Component({
  selector: 'app-get-together-form',
  imports: [ FormsModule,CommonModule],
  templateUrl: './get-together-form.component.html',
  styleUrl: './get-together-form.component.css'
})
export class GetTogetherFormComponent {

  constructor(private route:Router) { }

  formData = {
    title: '',
    description: '',
    eventDate: '',
    startTime: '',
    endTime: '',
    capacity: 0
  };

  submit() {
    const now = new Date();
    const eventDate = new Date(this.formData.eventDate);
    const startDateTime = new Date(`${this.formData.eventDate}T${this.formData.startTime}`);
    const endDateTime = new Date(`${this.formData.eventDate}T${this.formData.endTime}`);
  
    if (
      !this.formData.title.trim() ||
  !this.formData.description.trim() ||
  !this.formData.eventDate ||
  !this.formData.startTime ||
  !this.formData.endTime ||
  this.formData.capacity < 1
    ) {
      Swal.fire({
        title: 'Error',
        text: 'All fields are required!',
        icon: 'error',
        confirmButtonText: 'OK',
        customClass: {
          confirmButton: 'btn btn-danger'
        }
      });
      return;
    }
  
    if (eventDate < new Date(now.toDateString())) {
      Swal.fire({
        title: 'Invalid Date',
        text: 'Event date cannot be in the past.',
        icon: 'error',
        confirmButtonText: 'OK',
        customClass: {
          confirmButton: 'btn btn-danger'
        }
      });
      return;
    }
  
    if (startDateTime >= endDateTime) {
      Swal.fire({
        title: 'Invalid Time',
        text: 'Start time must be before end time.',
        icon: 'error',
        confirmButtonText: 'OK',
        customClass: {
          confirmButton: 'btn btn-danger'
        }
      });
      return;
    }
  
    Swal.fire({
      title: 'Success!',
      text: 'Event submitted successfully!',
      icon: 'success',
      confirmButtonText: 'OK',
      customClass: {
        confirmButton: 'btn btn-primary'
      },
      timer: 3000
    });
  
    console.log(this.formData);
    localStorage.removeItem('FormData');
    localStorage.setItem('FormData', JSON.stringify({
      eventType: 'GET_TOGETHER',
      title: this.formData.title,
      description: this.formData.description,
      eventDate: this.formData.eventDate,
      startTime: this.formData.startTime,
      endTime: this.formData.endTime,
      headCount: this.formData.capacity
    }));
    this.route.navigate(["/event/venue-selection"]);
  }
}
