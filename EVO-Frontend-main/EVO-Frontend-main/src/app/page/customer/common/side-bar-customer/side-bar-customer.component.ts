import { CommonModule, NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../../../../service/auth-service/auth.service';

@Component({
  selector: 'app-side-bar-customer',
  imports: [RouterLink,RouterLinkActive,CommonModule],
  templateUrl: './side-bar-customer.component.html',
  styleUrl: './side-bar-customer.component.css'
})
export class SideBarCustomerComponent {
  constructor(private authService:AuthService){}
  isSelect: number = 0;
  isSidebarClosed: boolean = false;

  isActive(num: number) {
    this.isSelect = num;
  }
    toggleSidebar() {

      this.isSidebarClosed = !this.isSidebarClosed;
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

}


