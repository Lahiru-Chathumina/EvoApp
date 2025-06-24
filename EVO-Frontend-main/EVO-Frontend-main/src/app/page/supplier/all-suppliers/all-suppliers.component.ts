import { Component, OnInit } from '@angular/core';
import Supplier from '../../../model/supplier';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { SupplierService } from '../../../../service/supplier-services/supplierService';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-all-suppliers',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './all-suppliers.component.html',
  styleUrls: ['./all-suppliers.component.css'],
})
export class AllSuppliersComponent implements OnInit {
  suppliersDull = [
  {
    id: 1,
    businessName: "Golden Frame Photography",
    description: "Capturing timeless memories for weddings and events.",
    category: "Photography",
    budget: "High",
    profilePictureUrl: "https://images.pexels.com/photos/2403395/pexels-photo-2403395.jpeg?auto=compress&cs=tinysrgb&w=600"
  },
  {
    id: 2,
    businessName: "Elite Event Decorators",
    description: "Expert decorators for weddings, engagements, and corporate events.",
    category: "Decor",
    budget: "Medium",
    profilePictureUrl: "https://images.pexels.com/photos/169189/pexels-photo-169189.jpeg?auto=compress&cs=tinysrgb&w=600"
  },
  {
    id: 3,
    businessName: "Taste Delight Catering",
    description: "Delicious and customizable catering menus for all occasions.",
    category: "Catering",
    budget: "Low",
    profilePictureUrl: "https://images.pexels.com/photos/5088028/pexels-photo-5088028.jpeg?auto=compress&cs=tinysrgb&w=600"
  }
];


  suppliers: any[] = this.suppliersDull;

  filteredSuppliers: any[] = [];
  popularity: string = 'Most Popular';
  budget: string = 'Any Budget';
  serviceType: string = 'All Services';
  searchQuery: string = '';

  constructor(private supplierService: SupplierService) {}

  ngOnInit(): void {
    this.supplierService.getAllSuppliers().subscribe(
  (data) => {
    console.log(data);
    
    this.suppliers = data;
  },
  (error) => {
    console.error('Error fetching suppliers:', error);
  }
);

  }

  applyFilters(): void {
    let result = [...this.suppliers];

    if (this.serviceType !== 'All Services') {
      result = result.filter(s => s.userType === this.serviceType);
    }

    if (this.searchQuery.trim()) {
      const query = this.searchQuery.toLowerCase();
      result = result.filter(s => 
        s.businessName.toLowerCase().includes(query) ||
        s.businessDescription.toLowerCase().includes(query)
      );
    }

    if (this.budget !== 'Any Budget') {
      result = result.filter(s => {
        if (this.budget === 'Low') return s.supplierId <= 2;
        if (this.budget === 'Medium') return s.supplierId > 2 && s.supplierId <= 4;
        if (this.budget === 'High') return s.supplierId > 4;
        return true;
      });
    }

    if (this.popularity === 'Most Popular') {
      result.sort((a, b) => b.supplierId - a.supplierId); 
    } else if (this.popularity === 'Least Popular') {
      result.sort((a, b) => a.supplierId - b.supplierId);
    }

    this.suppliers = result;
  }

  isCreatedEvent():boolean{
    return localStorage.getItem("FormData") !== null;
  }
}