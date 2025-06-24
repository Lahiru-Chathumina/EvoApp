import { Injectable } from "@angular/core";
import { AgendaTask } from "../../app/model/AgendaTask";
import { map, Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../app/environment/env.test";

export interface AgendaData {
  id: number;
  date: string;
  time: string;
  tasks: Task[];
}

export interface Task {
  taskId: number;
  taskName: string;
  startTime: string;
  endTime: string;
  supplierId: number;
  supplierType: string;
}

@Injectable({
  providedIn: 'root'
})

export class SampleAgendaService {

  constructor(private http: HttpClient) { }

  private notifiedOngoingTasks = new Set<string>();

  // default fallback task to be displayed when no tasks are available - Lahiru20
  private agendaTasks: AgendaTask[] = [
    {
      title: 'No Tasks Available Right Now',
      description: 'If no tasks are available, add a task to the agenda or consider creating a new event for today.',
      start_time: "2025-04-10T14:50:00.000Z",
      end_time: "2025-04-10T14:52:00.000Z"
    },

    // --------------------------- TESTING DATA FOR ONGOING EVENT --------------------------

    // Sample parallel tasks for demonstration purposes with random gaps for 5 times - Lahiru20
    ...Array.from({ length: 5 }, (_, i) => {
      const start = new Date();
      start.setMinutes(start.getMinutes() + i * (Math.floor(Math.random() * 2) + 1));
      const end = new Date(start);
      end.setMinutes(start.getMinutes() + Math.min((Math.floor(Math.random() * 2) + 1), 2)); // max 2 minutes
      const title = [
        "Wedding Ceremony",
        "Reception Setup",
        "Guest Arrival",
        "Dinner Service",
        "Cake Cutting",
        "First Dance",
        "Photo Session",
        "Bouquet Toss",
        "Speeches",
        "Fireworks Show",
        "After Party"
      ];
      const descriptions = [
        "Ceremony at the venue",
        "Setting up the reception area",
        "Guests arriving at the venue",
        "Dinner service begins",
        "Cutting the wedding cake",
        "First dance of the couple",
        "Photo session with guests",
        "Bouquet toss event",
        "Speeches by family and friends",
        "Fireworks show to celebrate",
        "After party with DJ and dancing"
      ];
      return {
        title: title[i] || 'After Party' + (i),
        description: descriptions[i] || 'After Party Fireworks Event (' + (i) + `)`,
        start_time: start.toISOString(),
        end_time: end.toISOString(),
      };
    }),

    // Sample parallel tasks for demonstration purposes with random gaps for 5 times - Lahiru20
    ...Array.from({ length: 5 }, (_, i) => {
      const start = new Date();
      start.setMinutes(start.getMinutes() + i * (Math.floor(Math.random() * 5) + 1));
      const end = new Date(start);
      end.setMinutes(start.getMinutes() + Math.min((Math.floor(Math.random() * 5) + 1), 2)); // max 2 minutes
      const title = [
        "Bridal Makeup",
        "Groom's Preparation",
        "Venue Decoration",
        "Guest Welcome",
        "Wedding Vows",
        "Ring Exchange",
        "Group Photoshoot",
        "Dinner Toasts",
        "Dance Performances",
        "Farewell Ceremony",
        "Honeymoon Departure"
      ];
      const descriptions = [
        "Bridal makeup session before the ceremony",
        "Groom getting ready for the big day",
        "Decorating the venue with flowers and lights",
        "Welcoming guests with refreshments",
        "Exchange of wedding vows by the couple",
        "Ring exchange ceremony",
        "Group photoshoot with family and friends",
        "Toasts and speeches during dinner",
        "Dance performances by the couple and guests",
        "Farewell ceremony for the couple",
        "Departure for the honeymoon destination"
      ];
      return {
        title: title[i] || 'After Party (' + (i) + ')',
        description: descriptions[i] || 'After Party Fireworks Event (' + (i) + `)`,
        start_time: start.toISOString(),
        end_time: end.toISOString(),
      };
    }),

    // Non-parallel tasks (sequential, no overlap) - Lahiru20
    ...(() => {
      const base = new Date();
      const tasks = [];
      const nonParallelTitles = [
        "Venue Opening",
        "Sound Check",
        "Lighting Setup",
        "Stage Preparation",
        "Final Inspection"
      ];
      const nonParallelDescriptions = [
        "Venue doors open for staff",
        "Checking sound system and microphones",
        "Setting up lighting for the event",
        "Preparing the stage for performances",
        "Final inspection before guests arrive"
      ];
      let lastEnd = new Date(base);
      for (let i = 0; i < nonParallelTitles.length; i++) {
        const start = new Date(lastEnd);
        start.setMinutes(start.getMinutes() + 1); // 1 min after previous ends
        const end = new Date(start);
        end.setMinutes(start.getMinutes() + 2); // Each task lasts max 2 min
        tasks.push({
          title: nonParallelTitles[i],
          description: nonParallelDescriptions[i],
          start_time: start.toISOString(),
          end_time: end.toISOString(),
        });
        lastEnd = end;
      }
      return tasks;
    })()

    // --------------------------- TESTING DATA FOR ONGOING EVENT --------------------------
  ];

  // Fallback task to be displayed when no tasks are available - Lahiru20
  private fallbackTask: AgendaTask = {
    title: 'No Tasks Available Right Now',
    description: 'If no tasks are available, add a task to the agenda or consider creating a new event for today.',
    start_time: "2025-04-10T14:50:00.000Z",
    end_time: "2025-04-10T14:52:00.000Z",
  };

  // get all tasks fitered from API - Lahiru20
  async getAllTasks(): Promise<AgendaTask[]> {
    await this.fetchAndMapTasks(1).subscribe(tasks => {
      this.agendaTasks.push(...tasks);
    });
    console.log(this.agendaTasks);
    return this.agendaTasks.length ? this.agendaTasks : [this.fallbackTask];
  }

  getFallbackTask(): AgendaTask {
    return this.fallbackTask;
  }

  // Fetch tasks from the API - Lahiru20
  getTasks(agendaId: number): Observable<Task[]> {
    const url = `${environment.baseUrl}/event/agenda/${agendaId}`;
    return this.http.get<AgendaData>(url).pipe(
      map(agenda => agenda.tasks)
    );
  }

  // Fetch and map tasks from the API - Lahiru20
  fetchAndMapTasks(agendaId: number): Observable<AgendaTask[]> {
    return this.getTasks(agendaId).pipe(
      map(tasks =>
        tasks.map(task => ({
          title: task.taskName,
          description: `Supplier Type: ${task.supplierType}`,
          start_time: task.startTime,
          end_time: task.endTime
        }))
      )
    );
  }

  //implementing the notification feature for notifying events - Lahiru20
  sendNotification(title: string, description: string) {
    if (this.checkNotificationSupport() && Notification.permission === 'granted') {
      const notification = new Notification(title + ' is ongoing right now!.', {
        body: '' + title
      });

      notification.onclick = (event) => {
        event.preventDefault();
        window.open('http://localhost:4200/customer/ongoing-event', '_blank');
      };
    }
  }

  checkNotificationSupport(): boolean {
    return 'Notification' in window;
  }


  getNotifiedOngoingTasks(): Set<string> {
    return this.notifiedOngoingTasks;
  }

  addNotifiedOngoingTask(title: string): void {
    this.notifiedOngoingTasks.add(title);
  }

  hasNotifiedOngoingTask(title: string): boolean {
    return this.notifiedOngoingTasks.has(title);
  }

}
