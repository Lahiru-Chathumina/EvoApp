import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import OLD_Venue from '../../../model/Venue';
import { VenuePagePopupComponent } from '../venue-page-popup/venue-page-popup.component';
import { FormsModule } from '@angular/forms';
import { SupplierService } from '../../../../service/supplier-services/supplierService';
import { VenueService } from '../../../../service/event-services/VenueService';

@Component({
  selector: 'app-venue-dashboard',
  standalone: true,
  imports: [ CommonModule, VenuePagePopupComponent ,FormsModule],
  templateUrl: './venue-dashboard.component.html',
  styleUrls: ['./venue-dashboard.component.css']
})
export class VenueDashboardComponent implements OnInit {
  eventDate: string = ""
  eventType: string = "";
  eventLocation: string = "";
  eventGuestCount: number = 0;  
  eventBudget: number = 0;
  eventTradition: string = "";
  eventStartTime: string = "";
  eventEndTime: string = "";

  constructor(private venueService:VenueService) { }
 
  ngOnInit(): void {
    const storedData: string | null = localStorage.getItem("FormData");
  
    if (storedData) {
      const formData = JSON.parse(storedData); 

      const date = new Date(formData.eventDate);
      this.eventDate = date.toISOString().substring(0, 10); 
      this.eventType = formData.eventType;
    } else {
      console.log("No FormData found in localStorage.");
    }

    this.setVenueSuppliers();
  }
  
  @ViewChild(VenuePagePopupComponent) venuePopup!: VenuePagePopupComponent;

  venues: any[] = [
    {
      id: 1, 
      supplierId: 1,
      name: "Shangri-La",
      location: "Panadura",
      type: 'VILLA',
      price: 25000,
      image: "https://images.pexels.com/photos/338504/pexels-photo-338504.jpeg?auto=compress&cs=tinysrgb&w=600",
      about: "Luxurious beachfront villa with panoramic ocean views",
      capacity: 150
    },
    {
      id: 2,
      supplierId: 2,
      name: "Tropical Paradise",
      location: "Galle",
      type: 'RESORT',
      price: 35000,
      image: "https://images.pexels.com/photos/1134176/pexels-photo-1134176.jpeg?auto=compress&cs=tinysrgb&w=600",
      about: "A serene resort surrounded by lush greenery",
      capacity: 200
    },
    {
      id: 3,
      supplierId: 3,
      name: "Ocean Breeze",
      location: "Hambantota",
      type: 'BEACH HOUSE',
      price: 40000,
      image: "https://images.pexels.com/photos/594077/pexels-photo-594077.jpeg?auto=compress&cs=tinysrgb&w=600",
      about: "A modern beach house with direct access to the ocean",
      capacity: 100
    }
  ];

  setVenueSuppliers(){
    this.venueService.getAllVenues(this.eventType).subscribe(
      (response) => {
        this.venues = response;
        console.log(this.venues);
        
  })}

  showVenuePopup(venue: OLD_Venue): void {
    this.venuePopup.showModal(venue);
  }
}
