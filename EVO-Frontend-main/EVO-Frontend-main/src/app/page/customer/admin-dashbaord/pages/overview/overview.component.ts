import { CommonModule } from '@angular/common'; 
import { Component, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { Chart, ChartConfiguration, ChartType, registerables } from 'chart.js/auto';

Chart.register(...registerables);
@Component({
  selector: 'app-overview',
  imports: [CommonModule],
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css']
})
export class OverviewComponent implements AfterViewInit  {
  @ViewChild('barCanvas') barCanvas!: ElementRef<HTMLCanvasElement>;
  @ViewChild('doughnutCanvas') doughnutCanvas!: ElementRef<HTMLCanvasElement>;

  barChart!: Chart;
  doughnutChart!: Chart;

  users = [
    { id: 1, name: 'nathasha', email: 'nathasha@gmail.com', role: 'Supplier' },
    { id: 2, name: 'shashini', email: 'johndoe@example.com', role: 'User' },
    { id: 1, name: 'nathasha', email: 'nathasha@gmail.com', role: 'Supplier' },
    { id: 2, name: 'shashini', email: 'johndoe@example.com', role: 'User' },
    { id: 1, name: 'nathasha', email: 'nathasha@gmail.com', role: 'Supplier' },
    { id: 2, name: 'shashini', email: 'johndoe@example.com', role: 'User' }
  ];
  
  ngAfterViewInit() {
    this.createBarChart();
    this.createDoughnutChart();
  }
  
  createBarChart() {
    const data = {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [{
        label: 'Monthly all Events',
        data: [65, 59, 80, 81, 56, 55, 40],
        backgroundColor: [
          'rgba(255, 99, 132, 0.2)',
          'rgba(255, 159, 64, 0.2)',
          'rgba(255, 205, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(201, 203, 207, 0.2)'
        ],
        borderColor: [
          'rgb(255, 99, 132)',
          'rgb(255, 159, 64)',
          'rgb(255, 205, 86)',
          'rgb(75, 192, 192)',
          'rgb(54, 162, 235)',
          'rgb(153, 102, 255)',
          'rgb(201, 203, 207)'
        ],
        borderWidth: 1
      }]
    };

    const config: ChartConfiguration = {
      type: 'bar' as ChartType,
      data: data,
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    };

    this.barChart = new Chart(this.barCanvas.nativeElement, config);
  }

createDoughnutChart() {
  const doughnutData = {
    lables:['Events'],
    labels: ['Weddings', 'Birthdays', 'Coporate'],
    datasets: [{
      label: 'My First Dataset',
      data: [300, 50, 100],
      backgroundColor: [
        'rgb(206, 21, 200)',
        'rgb(152, 156, 153)',
        'rgb(54, 162, 235)' 
      ],
      hoverOffset: 4
    }]
  };

  const doughnutConfig: ChartConfiguration = {
    type: 'doughnut' as ChartType,
    data: doughnutData,
    options: {
      responsive: true,
      maintainAspectRatio: false,
    }
  };

  this.doughnutChart = new Chart(this.doughnutCanvas.nativeElement, doughnutConfig);
}
}


