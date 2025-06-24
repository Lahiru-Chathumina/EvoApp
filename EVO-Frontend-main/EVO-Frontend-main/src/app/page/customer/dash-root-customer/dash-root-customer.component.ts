import { Component , AfterViewInit} from '@angular/core';
import { CommonModule } from '@angular/common';

import { Chart, LinearScale, CategoryScale, BarElement, BarController } from 'chart.js/auto';
Chart.register(LinearScale, CategoryScale, BarElement, BarController);

type Event = {
  name: string;
  date: string;
  status: string;
}

type Archives = {
  type: string;
  date: string;
  status: string;
  description: string;  
}


@Component({
  selector: 'app-dash-root-customer',
  // imports: [NgFor],
  imports: [CommonModule],
  templateUrl: './dash-root-customer.component.html',
  styleUrl: './dash-root-customer.component.css'
})
export class DashRootCustomerComponent implements AfterViewInit  {

  archiveApprovedStyle: string = "badge evo-bg-approved evo-green-text px-3 py-1 rounded-pill text-center";
  archivePendingStyle: string = "badge evo-bg-pending evo-pending-text px-3 py-1 rounded-pill text-center";
  archiveRejectedStyle: string = "badge evo-bg-rejected evo-rejected-text px-3 py-1 rounded-pill text-center";

  limit:number = 4;

  approvedEventCount = 0;
  pendingEventCount = 0;
  rejectedEventCount = 0;

  limitedEvents:Event[]=[];

  events:Event[] = [
    {
      name: "Birthday Party",
      date: new Date("2025-03-25").toDateString(),
      status: "Pending"
    },
    {
      name: "Parents Anniversary Party",
      date: new Date("2025-03-25").toDateString(),
      status: "Pending"
    },
    {
      name: "Baby's Birthday",
      date: new Date("2025-03-25").toDateString(),
      status: "Pending"
    },
    {
      name: "Get Together",
      date: new Date("2025-03-25").toDateString(),
      status: "Pending"
    },
    {
      name: "Wedding",
      date: new Date("2025-03-25").toDateString(),
      status: "Pending"
    }
  ];

  archives: Archives[] = [
    {
      type: "Birthday Party",
      date: new Date("2025-03-25").toDateString(),
      status: "Approved",
      description: "Gold Plan"
    },
    {
      type: "Birthday Party",
      date: new Date("2025-03-25").toDateString(),
      status: "Pending",
      description: "Gold Plan"
    },
    {
      type: "Birthday Party",
      date: new Date("2025-03-25").toDateString(),
      status: "Rejected",
      description: "Gold Plan"
    },
    {
      type: "Birthday Party",
      date: new Date("2025-03-25").toDateString(),
      status: "Approved",
      description: "Gold Plan"
    },
    {
      type: "Birthday Party",
      date: new Date("2025-03-25").toDateString(),
      status: "Approved",
      description: "Gold Plan"
    },
    {
      type: "Birthday Party",
      date: new Date("2025-03-25").toDateString(),
      status: "Approved",
      description: "Gold Plan"
    },
    {
      type: "Birthday Party",
      date: new Date("2025-03-25").toDateString(),
      status: "Approved",
      description: "Gold Plan"
    },
    {
      type: "Birthday Party",
      date: new Date("2025-03-25").toDateString(),
      status: "Approved",
      description: "Gold Plan"
    },
  ];

  constructor() {
    this.limitedEvents = this.events.slice(0, this.limit);
    this.archives.forEach((item)=>{
      if (item.status=="Approved") {
        this.approvedEventCount++;
      } else if (item.status=="Pending") {
        this.pendingEventCount++;
      } else if (item.status=="Rejected") {
        this.rejectedEventCount++;
      }
    })
  }

 
  ngAfterViewInit(): void {
 
    new Chart("myChart", {
      type: "doughnut",
      data: {
        labels: ['Pending', 'Approved', 'Rejected'],
        datasets: [{
          label: 'Event Status',
          data: [this.pendingEventCount, this.approvedEventCount, this.rejectedEventCount], 
          backgroundColor: [
            '#713F12',
            '#14532D',
            '#7F1D1D'
          ],
          borderColor: [
            '#713F12',
            '#14532D',
            '#7F1D1D'
          ],
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        cutout: '80%',
        plugins: {
          legend: {
            position: 'right'
          }
        },
        maintainAspectRatio: false
        
      }
    });
  }
  
}
