import { Component, OnInit } from '@angular/core';
import { RatingReviewsComponent } from "../rating-reviews/rating-reviews.component";
import OLD_PhotographerReview from '../../../model/PhotographerReview';
import OLD_PhotographerExtraFeature from '../../../model/PhotographerExtraFeature';
import OLD_PhotographerImage from '../../../model/PhotographerImage';
import OLD_PhotographerPackage from '../../../model/PhotographerPackage';
import { CommonModule } from '@angular/common';
import OLD_PhotographerBasicInfo from '../../../model/PhotographerBasicInfo';
import { ActivatedRoute, Route, Router, RouterLink } from '@angular/router';
import { SupplierService } from '../../../../service/supplier-services/supplierService';
import { getaSupplierFeture, getSupplierPackageById, getSupplierReviewById } from '../../../data/SupplierData';

@Component({
  selector: 'app-photographer-info',
  imports: [CommonModule, RouterLink, RatingReviewsComponent],
  templateUrl: './photographer-info.component.html',
  styleUrl: './photographer-info.component.css'
})
export class PhotographerInfoComponent implements OnInit {

  constructor(private supplierService: SupplierService, private route: ActivatedRoute, private router: Router) { }

  total_charges: number = 0;

  selectedPackage: string = '';

  basicinfo: OLD_PhotographerBasicInfo = { "title": "John Doe Photography", "rating": "five", "orders": 120, "description": "Professional wedding photographer" }

  features: OLD_PhotographerExtraFeature[] = [
    { "id": 1, "feature": "Drone Photography", "price": 300 },
    { "id": 2, "feature": "Photo Album", "price": 150 },
    { "id": 3, "feature": "HD Video Recording", "price": 500 },
    { "id": 4, "feature": "Instant Prints", "price": 100 },
    { "id": 5, "feature": "Extra Photographer", "price": 700 }
  ];

  images: OLD_PhotographerImage[] = [
    { "id": 1, "imageUrl": "https://i.pcmag.com/imagery/articles/07JoQVzt3zX0eQVLPDV0HyK-28..v1672948002.jpg" },
    { "id": 2, "imageUrl": "https://i.pcmag.com/imagery/articles/07JoQVzt3zX0eQVLPDV0HyK-28..v1672948002.jpg" },
    { "id": 3, "imageUrl": "https://i.pcmag.com/imagery/articles/07JoQVzt3zX0eQVLPDV0HyK-28..v1672948002.jpg" },
    { "id": 4, "imageUrl": "https://i.pcmag.com/imagery/articles/07JoQVzt3zX0eQVLPDV0HyK-28..v1672948002.jpg" },
    { "id": 5, "imageUrl": "https://i.pcmag.com/imagery/articles/07JoQVzt3zX0eQVLPDV0HyK-28..v1672948002.jpg" }
  ];

  packages: OLD_PhotographerPackage[] = [];

  reviews: any[] = [
    { "reviewId": 1, "rating": "five", "date": "2024-03-10", "comment": "Amazing service!" },
    { "reviewId": 2, "rating": "four", "date": "2024-03-08", "comment": "Great photos, highly recommend!" }
  ];



  supplier: any = this.basicinfo;

  selectedSupplier: any = null;

  ngOnInit(): void {

    const pathId = this.route.snapshot.params['id'];

    if (pathId == null) {
      this.router.navigate(['/supplier']);
    }
    //call apis
    this.packages = getSupplierPackageById(pathId);
    this.reviews = getSupplierReviewById(pathId);
    this.features = getaSupplierFeture(pathId);

    //get supplier basic info
    this.supplierService.searchSupplier(pathId).subscribe((response) => {
      if (response != null) {
        this.supplier = response;
      }
    });

    //get supplier feture info
    this.supplierService.supplierFetures(pathId).subscribe((response) => {
      this.features = response;
      if (response != null) {
      }
    });

    //get supplier pakcage info
    this.supplierService.supplierPackages(pathId).subscribe((response) => {
      if (response != null) {
      }
    });

    //getSuppleir riweis
    this.supplierService.supplierReviews(pathId).subscribe((response) => {
      if (response != null) {
      }
    });

  }

  dullSupplier: any = {

  }

  addSupplier() {

    let suppliers: any[] = [];
    // Retrieve existing array from localStoragelet suppliers = [];
    try {
      suppliers = JSON.parse(localStorage.getItem("suppliers") + "")
      || [];
    } catch (e) {
      console.error("Failed to parse suppliers from localStorage:", e);
    }
    

    // Add a new supplier to the array
    suppliers.push({
      //change these machingly to supplier dto
      id: this.supplier.title,
      businessName: this.supplier.title,
      imageUrl: this.supplier.imageUrl,
      description: this.supplier.description,
      rating: this.supplier.rating,
      reviews: this.supplier.reviews,
      packages: this.packages,
      features: this.features
    });
    console.log("two" + suppliers);
    //API request to add supplier 

    // Save updated array back to localStorage
    localStorage.setItem("suppliers", JSON.stringify(suppliers));
  }

  getTotal() {

  }

  onFeatureSelect(feature: OLD_PhotographerExtraFeature) {
    console.log(feature);
    this.total_charges += feature.price;
  }

  onPackageSelect(selectedPackage: OLD_PhotographerPackage, isSelected: boolean) {
    console.log(selectedPackage);

    if (isSelected) {
      // Add price when package is selected

      this.total_charges += selectedPackage.price;
      this.selectedPackage = selectedPackage.name;
    } else {
      // Subtract price when package is deselected
      this.total_charges -= selectedPackage.price;
      this.selectedPackage = ''; // or '' or undefined depending on your needs
    }
  }
}


