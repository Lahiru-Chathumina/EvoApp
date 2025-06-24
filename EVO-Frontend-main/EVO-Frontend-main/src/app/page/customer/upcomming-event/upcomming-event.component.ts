import { CommonModule, NgFor, NgIf, NgStyle } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { EventManagementService } from '../../../../service/event-services/EventManagementService';

interface Event {
  title: string;
  description: string;
  startTime: string;
  endTime: string;
}


@Component({
  selector: 'app-upcomming-event',
  standalone: true,
  imports: [NgFor, NgIf,CommonModule,RouterLink],
  templateUrl: './upcomming-event.component.html',
  styleUrls: ['./upcomming-event.component.css']
})
export class UpcommingEventComponent {
  showPopup: boolean = false;
  selectedEvent: Event | null = null;

  constructor(private eventmanagementservice:EventManagementService) {}

events: Event[] = [

  // Ongoing event: Now
  {
    title: 'Wedding Ceremony',
    description: 'Celebrating the union of two families.',
    startTime: new Date(Date.now() - 30 * 60 * 1000).toISOString(), // started 30 min ago
    endTime: new Date(Date.now() + 30 * 60 * 1000).toISOString()    // ends in 30 min
  },

  // Upcoming events: This week
  {
    title: 'Birthday Party',
    description: 'Celebrating a special birthday.',
    startTime: new Date(Date.now() + 2 * 60 * 60 * 1000).toISOString(), // in 2 hours
    endTime: new Date(Date.now() + 3 * 60 * 60 * 1000).toISOString()    // in 3 hours
  },
  {
    title: 'Anniversary',
    description: 'anniversary celebration.',
    startTime: new Date(Date.now() + 2 * 24 * 60 * 60 * 1000).toISOString(), // in 2 days
    endTime: new Date(Date.now() + 2 * 24 * 60 * 60 * 1000 + 2 * 60 * 60 * 1000).toISOString() // in 2 days + 2 hours
  },
  // {
  //   title: 'Get Together',
  //   description: 'Getting together with friends and family.',
  //   startTime: new Date(Date.now() + 5 * 24 * 60 * 60 * 1000).toISOString(), // in 5 days
  //   endTime: new Date(Date.now() + 5 * 24 * 60 * 60 * 1000 + 1 * 60 * 60 * 1000).toISOString() // in 5 days + 1 hour
  // }
];

  ongoingEvent: Event | null = null;
  upcomingEvents: Event[] = [];

  ngOnInit() {
    this.loadEventFromServiceToThisWeek();
    this.updateEvents();
    setInterval(() => {
      this.updateEvents();
    }, 60000);
  }

  updateEvents() {
    const now = new Date();
    const sevenDaysLater = new Date(now);
    sevenDaysLater.setDate(now.getDate() + 7);

    // event that is happening right now - Lahiru20
    this.ongoingEvent = this.events.find(event => {
      const start = new Date(event.startTime);
      const end = new Date(event.endTime);
      return start <= now && now <= end;
    }) || null;

    // Upcoming events this week - Lahiru20
    this.upcomingEvents = this.events
      .filter(event => {
        const start = new Date(event.startTime);
        return start > now;
      })
      .sort((a, b) => new Date(a.startTime).getTime() - new Date(b.startTime).getTime());
  }

  loadEventFromServiceToThisWeek() {
    this.eventmanagementservice.getAllSummary().subscribe((events: Event[]) => {
    this.events.push(...events);
    this.updateEvents();
  });
  }

}
