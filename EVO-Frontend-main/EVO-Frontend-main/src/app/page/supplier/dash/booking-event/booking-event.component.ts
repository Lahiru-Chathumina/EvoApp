import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import OLD_Booking from '../../../../model/Booking';
import { DatePipe } from '@angular/common';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-booking-event',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './booking-event.component.html',
  styleUrls: ['./booking-event.component.css']
})
export class BookingEventComponent {

  bookings: OLD_Booking[] = [];

  getData(): void {
    const datePipe = new DatePipe('en-US');
    const formattedDate = datePipe.transform(new Date(), 'yyyy-MM-dd') || ''; // Ensure it's a string

    this.bookings.push(new OLD_Booking(formattedDate, 'John Doe', 'Wedding', 'Confirmed'));
    this.bookings.push(new OLD_Booking(formattedDate, 'John Doe', 'Birthday', 'Confirmed'));
    this.bookings.push(new OLD_Booking(formattedDate, 'John Doe', 'Hotel', 'Confirmed'));
    this.bookings.push(new OLD_Booking(formattedDate, 'John Doe', 'Hotel', 'Confirmed'));
    this.bookings.push(new OLD_Booking(formattedDate, 'John Doe', 'Hotel', 'Confirmed'));
  }

  constructor() {
    this.getData();
  }

  popUpDetails() {
    Swal.fire({
      title: "Booking Details",
      text: "All the details of the booked event"
    });
  }
}
