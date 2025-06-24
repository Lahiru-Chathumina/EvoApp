import { CommonModule } from '@angular/common';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-ongoing-event-card',
  imports: [CommonModule],
  templateUrl: './ongoing-event-card.component.html',
  styleUrl: './ongoing-event-card.component.css'
})
export class OngoingEventCardComponent implements OnInit, OnDestroy{

  // input properties for the event card for get data from parent ongoingeventcomponent - Lahiru20
  @Input() title: string = '';
  @Input() description: string = '';
  @Input() start_time: string = '';
  @Input() end_time: string = '';

  timeLeft: { days: number, hours: number, minutes: number, seconds: number } = { days: 0, hours: 0, minutes: 0, seconds: 0 };
  progressPercentage: number = 0;
  isLive: boolean = false;
  isCompleted: boolean = false;
  hasNotStarted: boolean = false;
  private timerSubscription?: Subscription;

  ngOnInit() {
    // Initialize the timer on start - Lahiru20
    this.updateTimer();
    this.timerSubscription = interval(1000).subscribe(() => {
      this.updateTimer();
    });
  }

  ngOnDestroy() {
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
  }



  updateTimer() {
    const now = new Date().getTime();
    const startTime = new Date(this.start_time).getTime();
    const endTime = new Date(this.end_time).getTime();

    // Check event status - Lahiru20
    this.hasNotStarted = now < startTime;
    this.isLive = now >= startTime && now < endTime;
    this.isCompleted = now >= endTime;

    // Calculate time left - ALWAYS between start and end time - Lahiru20
    let timeRemaining = 0;

    if (this.isLive) {
      timeRemaining = endTime - now;
    } else if (this.hasNotStarted) {
      timeRemaining = endTime - startTime;
      this.progressPercentage = 0;
    } else {
      timeRemaining = 0;
      this.progressPercentage = 100;
    }

    if (timeRemaining > 0) {
      this.timeLeft.days = Math.floor(timeRemaining / (1000 * 60 * 60 * 24));
      this.timeLeft.hours = Math.floor((timeRemaining % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
      this.timeLeft.minutes = Math.floor((timeRemaining % (1000 * 60 * 60)) / (1000 * 60));
      this.timeLeft.seconds = Math.floor((timeRemaining % (1000 * 60)) / 1000);
    } else {
      this.timeLeft = { days: 0, hours: 0, minutes: 0, seconds: 0 };
    }

    // Calculate progress percentage - only for live events
    if (this.isLive) {
      const totalDuration = endTime - startTime;
      const elapsed = now - startTime;
      this.progressPercentage = Math.min(100, Math.round((elapsed / totalDuration) * 100));
    }
  }



checkNotificationSupport(): boolean {
  return 'Notification' in window;
}

  formatDateTime(isoString: string): string {
    const date = new Date(isoString);
    return date.toLocaleString();
  }
}
