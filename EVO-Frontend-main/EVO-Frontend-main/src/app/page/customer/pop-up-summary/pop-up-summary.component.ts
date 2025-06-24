import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-pop-up-summary',
  imports: [CommonModule],
  templateUrl: './pop-up-summary.component.html',
  styleUrl: './pop-up-summary.component.css'
})
export class PopUpSummaryComponent {


  public eventSummery:any = null;
  event:any = {
    eventType : "wedding",
    startDate :"2023/02/02",
    endDate :"2026/03/07",
    eventStatus:"Pending"
  };
  supplers:any[] = [{
    name:"Rocky"
  }];



  ngOnInit(): void {
      this.refeshData();
  }

  refeshData(){

  }

}
