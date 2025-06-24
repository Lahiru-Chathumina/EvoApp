import { Component, ViewChild, ElementRef, Input, OnInit } from '@angular/core';
import { NgxTimelineModule, NgxTimelineOrientation, NgxTimelineEventGroup, NgxTimelineEvent } from '@frxjs/ngx-timeline';
import { AgendaTask } from '../../../../model/AgendaTask';
import { SampleAgendaService } from '../../../../../service/event-services/SampleAgendaService';

@Component({
  selector: 'app-timeline',
  imports: [NgxTimelineModule],
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.css']
})
export class TimelineComponent implements OnInit {

  constructor(private agendaService: SampleAgendaService) { }

  @ViewChild('timelineContainer') timelineContainer!: ElementRef;

  readonly orientation = NgxTimelineOrientation.HORIZONTAL;
  readonly groupEvent = NgxTimelineEventGroup.DAY_MONTH_YEAR;

  @Input() timestamp: string = '';
  @Input() title: string = '';
  @Input() description: string = '';

  agenda_tasks: AgendaTask[] = [];

  fallbackTask!: AgendaTask;

  events: NgxTimelineEvent[] = [];

  private zoomLevel: number = 1; // Initial zoom level

  async ngOnInit() {

    this.agenda_tasks = await this.agendaService.getAllTasks();
    this.fallbackTask = this.agendaService.getFallbackTask();

    // this function is use to compare with current date and time and previous tasks of today and before now time - Lahiru20
    const now = new Date();
    this.agenda_tasks.forEach(task => {
      const taskDate = new Date(task.end_time);
      if (
      taskDate.toDateString() === now.toDateString() &&
      taskDate.getTime() < now.getTime()
      ) {
      this.events.push({
        timestamp: new Date(task.start_time),
        title: task.title,
        description: task.description
      });

      // Scroll to the end of the timeline container
      setTimeout(() => {
        this.timelineContainer.nativeElement.scrollTo({
          left: this.timelineContainer.nativeElement.scrollWidth,
          behavior: 'smooth'
        });
      }, 0);

      }
    });

    // this function is used to update the timeline - Lahiru20
    setInterval(() => {

      const newEvent: NgxTimelineEvent = {
        timestamp: new Date(this.timestamp),
        title: this.title,
        description: this.description
      };

      const existingEventIndex = this.events.findIndex(
        event => event.timestamp.getTime() === newEvent.timestamp.getTime()
      );

      if (existingEventIndex !== -1) {
        this.events[existingEventIndex] = newEvent;
      } else {
        if(newEvent.title !== this.fallbackTask.title) {
          this.events.push(newEvent);

      // Scroll to the end of the timeline container - Lahiru20
      setTimeout(() => {
        this.timelineContainer.nativeElement.scrollTo({
          left: this.timelineContainer.nativeElement.scrollWidth,
          behavior: 'smooth'
        });
      }, 0);

        }
      }
    }, 1000);
  }

  // Scroll function buttons and Zoom fuction buttons - Lahiru20
  scrollRight() {
    this.timelineContainer.nativeElement.scrollBy({
      left: 300,
      behavior: 'smooth'
    });
  }

  scrollLeft() {
    this.timelineContainer.nativeElement.scrollBy({
      left: -300,
      behavior: 'smooth'
    });
  }

  zoomIn() {
    if (this.zoomLevel < 1.2) {
      this.zoomLevel += 0.1;
      this.timelineContainer.nativeElement.style.zoom = this.zoomLevel.toString();
    }
  }

  zoomOut() {
    if (this.zoomLevel > 0.5) {
      this.zoomLevel -= 0.1;
      this.timelineContainer.nativeElement.style.zoom = this.zoomLevel.toString();
    }
  }
}
