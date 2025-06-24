import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventSupplierCardComponent } from '../event-supplier-card/event-supplier-card.component';
import { HttpClient } from '@angular/common/http';
import { EventCardService } from '../../../../../service/event-services/EventCardService';
@Component({
  selector: 'app-event-card',
  imports: [ EventSupplierCardComponent,CommonModule],
  templateUrl: './event-card.component.html',
  styleUrl: './event-card.component.css'
})
export class EventCardComponent implements OnInit {
  @Input() public eventDetails: any | Event ;
  isFirstVar: Boolean = true;


  ngOnInit(): void {
    console.log(this.eventDetails);
  }

  public isFirst(): Boolean {
    if (this.isFirstVar) {
      this.isFirstVar = false;
      return true;
    }
    return false;
  }

  constructor(private http: HttpClient){}


}
