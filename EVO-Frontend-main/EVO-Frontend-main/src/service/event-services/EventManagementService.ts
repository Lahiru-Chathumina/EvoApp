import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../app/environment/env.test';

@Injectable({
  providedIn: 'root'
})
export class EventManagementService {
  constructor(private http: HttpClient) {}

  getAllSummary(): Observable<any[]> {
    return this.http.get<any[]>(`${environment.baseUrl}/event/summary/by-user/1`).pipe(
      map(events => this.transformEvents(events))
    );
  }

  getAllSummaryThisWeek(): Observable<any[]> {
    return this.http.get<any[]>(`${environment.baseUrl}/event/summary/by-user/1`).pipe(
      map(events => this.filterWeekAndTransformEvents(events))
    );
  }

  private transformEvents(events: any[]): any[] {
    return events.map(event => ({
      title: this.getDisplayNameByEventType(event.eventType),
      description: this.getDescriptionByEventType(event.eventType),
      startTime: new Date(`${event.eventDate}T${event.startTime}`).toISOString(),
      endTime: new Date(`${event.eventDate}T${event.endTime}`).toISOString()
    }));
  }

  private filterWeekAndTransformEvents(events: any[]): any[] {
    const currentDate = new Date();
    const startOfWeek = this.getStartOfWeek(currentDate);
    const endOfWeek = this.getEndOfWeek(currentDate);

    return events
      .filter(event => {
        const eventStart = new Date(`${event.eventDate}T${event.startTime}`);
        return eventStart >= startOfWeek && eventStart <= endOfWeek;
      })
      .map(event => ({
        title: this.getDisplayNameByEventType(event.eventType),
        description: this.getDescriptionByEventType(event.eventType),
        startTime: new Date(`${event.eventDate}T${event.startTime}`).toISOString(),
        endTime: new Date(`${event.eventDate}T${event.endTime}`).toISOString()
      }));
  }

  private getDescriptionByEventType(eventType: string): string {
    switch (eventType) {
      case 'WEDDING':
        return 'Elegant celebrations for your big day.';
      case 'BIRTHDAY_PARTIES':
        return 'Fun-filled moments for every age.';
      case 'GET_TOGETHER':
        return 'Reconnect and create lasting memories.';
      case 'ANNIVERSARIES':
        return 'Celebrate love and cherished milestones.';
      default:
        return 'Special event to create unforgettable memories.';
    }
  }

  private getDisplayNameByEventType(eventType: string): string {
    switch (eventType) {
      case 'WEDDING':
        return 'Wedding Ceremony';
      case 'BIRTHDAY_PARTIES':
        return 'Birthday Party';
      case 'GET_TOGETHER':
        return 'Get Together';
      case 'ANNIVERSARIES':
        return 'Anniversary';
      default:
        return 'Event';
    }
  }

  private getStartOfWeek(date: Date): Date {
    const start = new Date(date);
    const day = start.getDay();
    const diff = start.getDate() - day + (day === 0 ? -6 : 1); // Adjust when day is Sunday - Lahiru20
    start.setDate(diff);
    start.setHours(0, 0, 0, 0);
    return start;
  }

  private getEndOfWeek(date: Date): Date {
    const end = this.getStartOfWeek(date);
    end.setDate(end.getDate() + 6);
    end.setHours(23, 59, 59, 999);
    return end;
  }

}
