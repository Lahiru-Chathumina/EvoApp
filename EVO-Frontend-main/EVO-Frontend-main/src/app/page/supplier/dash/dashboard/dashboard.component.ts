import { Component } from '@angular/core';
import { SideBarComponent } from "../../common/side-bar/side-bar.component";
import { AddPreviousComponent } from "../add-previous/add-previous.component";
import { BookingEventComponent } from "../booking-event/booking-event.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [SideBarComponent,RouterOutlet],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
