import { Component, Input, OnDestroy, OnInit } from '@angular/core';

import { BehaviorSubject, interval, Subscription } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { AgendaTask } from '../../../model/AgendaTask';
import { TimelineComponent } from './timeline/timeline.component';
import { OngoingEventCardComponent } from './ongoing-event-card/ongoing-event-card.component';
import { SampleAgendaService } from '../../../../service/event-services/SampleAgendaService';

@Component({
  selector: 'app-ongoing-event',
  imports: [TimelineComponent, OngoingEventCardComponent, FormsModule, CommonModule],
  templateUrl: './ongoing-event.component.html',
  styleUrls: ['./ongoing-event.component.css'],
})

export class OngoingEventComponent implements OnInit, OnDestroy {
  @Input() agendaTask: AgendaTask[] = [];
  fallbackTask!: AgendaTask;

  currentTasks$ = new BehaviorSubject<AgendaTask[]>([]);
  private updateSubscription!: Subscription;
  private notificationSubscription!: Subscription;
  constructor(private agendaService: SampleAgendaService) {}

  async ngOnInit() {
  if (this.checkNotificationSupport() && Notification.permission !== 'granted') {
    await Notification.requestPermission();
  }
    const allTasks = await this.agendaService.getAllTasks();
    this.agendaTask = allTasks.filter(task => task.title !== 'No Tasks Available Right Now');
    this.fallbackTask = this.agendaService.getFallbackTask();
    this.updateTaskInfo();
    this.updateSubscription = interval(12000).subscribe(() => this.updateTaskInfo()); // Update tasks every 12 seconds - Lahiru20
    this.agendaTask.forEach(task => this.checkNotificationStatus(task));
    this.notificationSubscription = interval(60000).subscribe(() => {
      this.agendaTask.forEach(task => this.checkNotificationStatus(task));
    }); // Update tasks every minute - Lahiru20
  }



  private updateTaskInfo() {
    const now = new Date();
    const ongoingTasks = this.findCurrentTasks(now);

    if (ongoingTasks.length > 0) {
      this.currentTasks$.next(ongoingTasks);
    } else {
      this.currentTasks$.next([this.fallbackTask]);
    }
  }

  private findCurrentTasks(now: Date): AgendaTask[] {
    return this.agendaTask.filter(task => {
      const start = new Date(task.start_time);
      const end = new Date(task.end_time);
      return now >= start && now <= end;
    });
  }

  isOngoing(startTime: string, endTime: string): boolean {
    const now = new Date().getTime();
    return new Date(startTime).getTime() <= now && now <= new Date(endTime).getTime();
  }

  getCountdown(endTime: string): string {
    const now = new Date().getTime();
    const end = new Date(endTime).getTime();
    const diff = end - now;

    if (diff <= 0) {
      return '00:00:00';
    }

    const hours = Math.floor(diff / (1000 * 60 * 60));
    const minutes = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
    const seconds = Math.floor((diff % (1000 * 60)) / 1000);

    return `${this.pad(hours)}:${this.pad(minutes)}:${this.pad(seconds)}`;
  }

  private pad(num: number): string {
    return num < 10 ? '0' + num : num.toString();
  }

  getGroupedTaskDescription(tasks: AgendaTask[]): string {
    if (tasks.length === 1) {
      return tasks[0].description;
    } else if (tasks.length > 1) {
      return `` + tasks.map(task => task.title).join(' | ');
    }
    return '';
  }

  getTimelineTitle(tasks: AgendaTask[]): string {
    return tasks.length === 1 ? tasks[0].title : 'Tasks in Parallel (' + tasks.length + ')';
  }

  getTaskProgress(task: AgendaTask): string {
    const now = new Date();
    const start = new Date(task.start_time);
    const end = new Date(task.end_time);

    if (now < start) {
      return '0%';
    } else if (now > end) {
      return '100%';
    } else {
      const totalDuration = end.getTime() - start.getTime();
      const elapsedTime = now.getTime() - start.getTime();
      const progress = Math.min((elapsedTime / totalDuration) * 100, 100);
      return `${progress.toFixed(0)}%`;
    }
  }

  getTaskStatus(task: AgendaTask): string {
    const now = new Date();
    const start = new Date(task.start_time);
    const end = new Date(task.end_time);

    if (now < start) {
      return 'Upcoming';
    } else if (now > end) {
      return 'Completed';
    } else {
      return 'Ongoing';
    }
  }

  getStatusClass(task: AgendaTask): string {
    const status = this.getTaskStatus(task);
    switch (status) {
      case 'Upcoming':
        return 'status-upcoming';
      case 'Ongoing':
        return 'status-ongoing';
      case 'Completed':
        return 'status-completed';
      default:
        return '';
    }
  }

  checkNotificationStatus(task: AgendaTask) {
    const status = this.getTaskStatus(task);

    if (
      status === 'Ongoing' &&
      !this.agendaService.hasNotifiedOngoingTask(task.title)
    ) {
      this.agendaService.sendNotification(task.title, `Task is ongoing: ${task.description}`);
      this.agendaService.addNotifiedOngoingTask(task.title);
    }
  }

 checkNotificationSupport(): boolean {
  return 'Notification' in window;
 }


  ngOnDestroy() {
    if (this.updateSubscription) {
      this.updateSubscription.unsubscribe();
    }
  }
}


