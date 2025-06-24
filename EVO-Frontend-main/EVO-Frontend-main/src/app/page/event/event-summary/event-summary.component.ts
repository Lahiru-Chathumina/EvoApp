import { CommonModule, NgStyle } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { VenueService } from '../../../../service/event-services/VenueService';
import Supplier from '../../../model/supplier/Supplier';
import { SupplierService } from '../../../../service/supplier-services/supplierService';
import { eventService } from '../../../../service/event-services/eventService';

@Component({
  selector: 'app-event-summary',
  standalone: true,
  imports: [RouterLink, NgStyle, CommonModule],
  templateUrl: './event-summary.component.html',
  styleUrls: ['./event-summary.component.css']
})
export class EventSummaryComponent implements OnInit {

  constructor(private venueService: VenueService, private supplierService: SupplierService) { }

  public eventObject: any = null;
  public eventDetails: any = null;

  public eventSummery: any = null;
  event: any = {
    eventType: "",
    startDate: "2023/02/02",
    endDate: "2026/03/07",
    eventStatus: "Pending"
  };

  suppliers: any[] = [{
    name: "Rocky",
    type: "Catering",
    image: "https://cdn.pixabay.com/photo/2025/03/07/13/12/flower-9453063_1280.jpg",
    description: "Catering service for all events",
    price: 25000,
    aveilabiility: "Available"
  }, {
    name: "Rocky",
    aveilabiility: "Pending"

  }, {
    name: "Rocky",
    aveilabiility: "Aveilable"

  }];

  venue: any = {
    name: "Shangri-La",
    location: "Panadura",
    type: "VILLA",
    price: 25000,
    image: "https://cdn.pixabay.com/photo/2025/03/07/13/12/flower-9453063_1280.jpg",
    description: "Luxurious beachfront villa with panoramic ocean views",
    capacity: 150
  }

  totalPrice: number = 0;

  ngOnInit(): void {
    this.getEventObject(localStorage.getItem('eventId'))
    this.getEventData()
    console.log(this.eventDetails.eventType)
    this.getVenueData();
    this.totalPrice = this.calculateTotalPrice();
    this.getSupplierData();

  }

  getEventObject(id: any) {
    //  this.eventObject = this.eventService.getEvent(id);
  }

  getEventData() {
    const storedData = localStorage.getItem("FormData");
    console.log(storedData);

    if (storedData) {
      this.eventDetails = JSON.parse(storedData);
    } else {
      console.log("No FormData found in localStorage.");
    }
  }

  getVenueData() {
    const venue = localStorage.getItem("Venue");

    if (venue) {
      const storedData = JSON.parse(venue);
      this.venue = storedData;
    } else {
      console.log("No venue id in localStorage.");
    }
  }


  calculateTotalPrice() {
    let total = this.venue.price;
    this.suppliers.forEach((supplier) => {
      if (supplier.aveilabiility === "Available") {
        total += supplier.price
      }
    });

    const formData = JSON.parse(localStorage.getItem("FormData")!);

    formData["totalPrice"] = total;
    localStorage.setItem("FormData", JSON.stringify(formData))

    return total;
  }

  getSupplierData() {
    const suppliers = JSON.parse(localStorage.getItem("suppliers") + "") || [];
    this.suppliers = suppliers
    console.log(suppliers);
  }
}
