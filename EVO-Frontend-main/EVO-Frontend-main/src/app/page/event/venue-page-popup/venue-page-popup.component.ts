import { Component, ViewChild, ElementRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import OLD_Venue from '../../../model/Venue';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-venue-page-popup',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './venue-page-popup.component.html',
  styleUrls: ['./venue-page-popup.component.css']
})
export class VenuePagePopupComponent {
  @ViewChild('venueModal') modalElement!: ElementRef;
  currentVenue: any | null = null;

  imageGallery = [
    { "src": "https://cdn.pixabay.com/photo/2025/03/07/13/12/flower-9453063_1280.jpg" },
    { "src": "https://cdn.pixabay.com/photo/2025/03/07/13/12/flower-9453063_1280.jpg" },
    { "src": "https://cdn.pixabay.com/photo/2025/03/07/13/12/flower-9453063_1280.jpg" },
    { "src": "https://cdn.pixabay.com/photo/2025/03/07/13/12/flower-9453063_1280.jpg" }
  ];

  constructor(private router: Router) {}

  showModal(venue: OLD_Venue): void {
    this.currentVenue = venue;
    const modal = new bootstrap.Modal(this.modalElement.nativeElement);
    modal.show();
  }

  bookNow(): void {
    if (this.currentVenue) {
      this.router.navigate(['/event/event-summery']);
        localStorage.setItem("Venue",JSON.stringify(this.currentVenue));
    }
  }
}
