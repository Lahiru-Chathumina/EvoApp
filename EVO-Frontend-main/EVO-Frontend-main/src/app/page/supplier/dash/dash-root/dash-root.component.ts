

import { Component, AfterViewInit, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import Chart from 'chart.js/auto';
import { OLD_DashboardData } from '../../../../model/Supplier-dash-root';
import { environment } from '../../../../environment/env.test';

@Component({
  selector: 'app-dash-root',
  imports: [HttpClientModule],
  templateUrl: './dash-root.component.html',
  styleUrl: './dash-root.component.css'
})
export class DashRootComponent implements OnInit, AfterViewInit {
  dashboardData: OLD_DashboardData = new OLD_DashboardData();

  constructor(private http: HttpClient) {}

  ngAfterViewInit() {
    this.loadBarChart();
    this.loadPieChart();
  }

  ngOnInit(): void {
    this.loadWorks();
    this.loadRatings();
    this.loadCustomers();
  }

  loadWorks() {
    this.http.get<any[]>(environment.baseUrl+'/supplier/previous-work/get-all')
      .subscribe({
        next: (res) => {
          this.dashboardData.totalWorks = res.length;
        }
      });
  }

  loadRatings() {
    this.http.get<any[]>(environment.baseUrl+'/review/get-all')
      .subscribe({
        next: (res) => {
          this.dashboardData.totalRatings = res.length;
        }
      });
  }

  loadCustomers() {
    this.http.get<any[]>(environment.baseUrl+'/userController/users')
      .subscribe({
        next: (res) => {
          this.dashboardData.customers = res.filter(user => user.userType === 'customer').length;
        },
        error: (err) => {
          console.error('Error fetching users:', err);
        }
      });
  }

  loadBarChart() {
    const barChart = new Chart("barChart", {
      type: "bar",
      data: {
        labels: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
        datasets: [{
          label: "Daily Logins",
          data: [12, 19, 3, 5, 2, 3, 7],
          backgroundColor: "rgba(75, 192, 192, 0.6)"
        }]
      }
    });
  }

  loadPieChart() {
    const pieChart = new Chart("pieChart", {
      type: "pie",
      data: {
        labels: ["Basic", "Premium", "Exclusive"],
        datasets: [{
          label: "Popularity",
          data: [30, 45, 25],
          backgroundColor: ["#ff6384", "#36a2eb", "#ffce56"]
        }]
      }
    });
  }
}
