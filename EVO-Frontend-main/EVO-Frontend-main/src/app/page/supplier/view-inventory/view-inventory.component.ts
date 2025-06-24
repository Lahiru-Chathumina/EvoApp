import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import OLD_Inventory  from '../../../model/Inventory';

@Component({
  selector: 'app-view-inventory',
  imports: [CommonModule],
  templateUrl: './view-inventory.component.html',
  styleUrl: './view-inventory.component.css'
})
export class ViewInventoryComponent {

  inventories: OLD_Inventory[] = [];

  loadData():void{
      this.inventories.push(new OLD_Inventory('Camera','https://images.pexels.com/photos/249597/pexels-photo-249597.jpeg?auto=compress&cs=tinysrgb&w=600','with 2 lens','CAMERA'));
      this.inventories.push(new OLD_Inventory('Camera','https://images.pexels.com/photos/249597/pexels-photo-249597.jpeg?auto=compress&cs=tinysrgb&w=600','with 2 lens','CAMERA'));
      this.inventories.push(new OLD_Inventory('Camera','https://images.pexels.com/photos/249597/pexels-photo-249597.jpeg?auto=compress&cs=tinysrgb&w=600','with 2 lens','CAMERA'));
      this.inventories.push(new OLD_Inventory('Camera','https://images.pexels.com/photos/249597/pexels-photo-249597.jpeg?auto=compress&cs=tinysrgb&w=600','with 2 lens','CAMERA'));
      this.inventories.push(new OLD_Inventory('Camera','https://images.pexels.com/photos/249597/pexels-photo-249597.jpeg?auto=compress&cs=tinysrgb&w=600','with 2 lens','CAMERA'));


  }

  constructor(){
    this.loadData();
  }
}
