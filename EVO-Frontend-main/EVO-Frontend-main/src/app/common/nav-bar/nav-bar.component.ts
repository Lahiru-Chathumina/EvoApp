import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SampleAgendaService } from '../../../service/event-services/SampleAgendaService';
import { AuthService } from '../../../service/auth-service/auth.service';

@Component({
  selector: 'app-nav-bar',
  imports: [RouterLink, CommonModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {
  private notifiedOngoingTasks = new Set<string>();
  notifications = [
    { title: 'Welcome!', body: 'Thanks for using EvoPlan.' },
  ];

  isNavbarCollapsed = true;

  constructor(
    private agendaservice: SampleAgendaService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    setInterval(() => {
      const ongoingTasks = this.agendaservice.getNotifiedOngoingTasks();
      ongoingTasks.forEach((task: string) => {
        if (!this.notifiedOngoingTasks.has(task)) {
          this.notifiedOngoingTasks.add(task);
          this.notifications.push({
            title: `${task}`,
            body: `Task "${task}" is ongoing.`,
          });
        }
      });
    }, 5000);
  }

  toggleNavbar() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  get userEmail(): string | null {
    return this.authService.getUsername();
  }

  get userRole(): string | null {
    return this.authService.getRole();
  }

  get isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  get userProfileImage(): string {
    return this.authService.getProfileImage();
  }

  get userFullName(): string {
    return this.authService.getFullName();
  }

  get roleClass(): string {
    switch (this.userRole) {
      case 'CUSTOMER': return 'customer';
      case 'SUPPLIER': return 'supplier';
      case 'ADMIN': return 'admin';
      default: return '';
    }
  }

  logout() {
    this.authService.logout();
  }
}
