import { Component } from '@angular/core';
import { NavBarComponent } from "../../../common/nav-bar/nav-bar.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-event-root',
  imports: [NavBarComponent, RouterOutlet],
  templateUrl: './event-root.component.html',
  styleUrl: './event-root.component.css'
})
export class EventRootComponent {

}
