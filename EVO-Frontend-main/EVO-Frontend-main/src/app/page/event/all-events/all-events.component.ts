import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import OLD_Venue from '../../../model/Venue';
import { getLocaleDayPeriods, NgFor } from '@angular/common';
import * as bootstrap from 'bootstrap';
import { EventCardComponent } from "./event-card/event-card.component";
import { environment } from '../../../environment/env.test';
import { get } from 'jquery';
import { EventCardService } from '../../../../service/event-services/EventCardService';


@Component({
  selector: 'app-all-events',
  imports: [RouterLink, NgFor, EventCardComponent],
  templateUrl: './all-events.component.html',
  styleUrl: './all-events.component.css'
})
export class AllEventsComponent {

  @ViewChild('venueModal') modalElement!: ElementRef;
  currentVenue: OLD_Venue | null = null;
  isFirstVar: Boolean = true;

  imageGallery = [
    { "src": "https://cdn.pixabay.com/photo/2025/03/07/13/12/flower-9453063_1280.jpg" },
    { "src": "https://cdn.pixabay.com/photo/2025/03/07/13/12/flower-9453063_1280.jpg" },
    { "src": "https://cdn.pixabay.com/photo/2025/03/07/13/12/flower-9453063_1280.jpg" },
    { "src": "https://cdn.pixabay.com/photo/2025/03/07/13/12/flower-9453063_1280.jpg" }
  ];

  eventDetails: any | Event = [];
  
  constructor(private router: Router ,private eventCardService: EventCardService) {
    this.setAllEvents();
   }

  showModal(venue: OLD_Venue): void {
    this.currentVenue = venue;
    const modal = new bootstrap.Modal(this.modalElement.nativeElement);
    modal.show();
  }

  bookNow(): void {
    if (this.currentVenue) {

      this.router.navigate(['/event/event-summery']);

    }
  }

  isFirst(): Boolean {
    if (this.isFirstVar) {
      this.isFirstVar = false;
      return true;
    }
    return false;
  }

  setAllEvents() {
    this.eventCardService.getAllEvents().subscribe((eventList: Event[]) => {  {
        this.eventDetails = eventList;
      console.log(this.eventDetails);
    } }
    , (error) => {
      console.error('Error fetching events:', error);
    }
    );
  }
}