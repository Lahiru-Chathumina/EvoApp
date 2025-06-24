import { Component } from '@angular/core';
import { SideBarCustomerComponent } from "../common/side-bar-customer/side-bar-customer.component";
import { RouterOutlet } from '@angular/router';
import { NavBarComponent } from "../../../common/nav-bar/nav-bar.component";

@Component({
  selector: 'app-customer-dashboard',
  imports: [SideBarCustomerComponent, RouterOutlet, NavBarComponent],
  templateUrl: './customer-dashboard.component.html',
  styleUrl: './customer-dashboard.component.css'
})
export class CustomerDashboardComponent {

}
