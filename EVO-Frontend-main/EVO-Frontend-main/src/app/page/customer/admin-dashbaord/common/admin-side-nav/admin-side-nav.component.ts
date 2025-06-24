import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../../../../../service/auth-service/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-side-nav',
  imports: [RouterLink,RouterLinkActive,CommonModule],
  templateUrl: './admin-side-nav.component.html',
  styleUrl: './admin-side-nav.component.css'
})
export class AdminSideNavComponent {

  constructor(private authService:AuthService) { }

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

}
