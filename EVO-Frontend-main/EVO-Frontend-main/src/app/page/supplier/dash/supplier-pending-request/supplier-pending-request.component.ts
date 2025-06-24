import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { SupplerRequestService } from '../../../../../service/supplier-request/SupplierRequestService';
import { SupplierRequest } from '../../../../model/supplier/SupplierRequest';
import { NgFor } from '@angular/common';
import { SupplerRequestStatusType } from '../../../../../utils/SupplerRequestStatusType';

import { ProfilePackage } from '../../../../model/supplier/ProfilePackage';
import { Customer } from '../../../../model/customer/Customer';
import Supplier from '../../../../model/supplier/Supplier';


@Component({
  selector: 'app-supplier-pending-request',
  imports: [NgFor],

  templateUrl: './supplier-pending-request.component.html',
  styleUrl: './supplier-pending-request.component.css'
})
export class SupplierPendingRequestComponent implements OnInit {

  customerName:string="Udayanga";
  packageName:string=""
  extras:string=""
  request_date:string=""
  requestTime:string=""
  location:string=""
  price:number=0;

  listOfSupplierRequests:SupplierRequest[] =[];

  

  supplierID: number = 1; 
  supplierRequest: SupplierRequest 

  

  constructor(private http:HttpClient,private supplierRequestService:SupplerRequestService ){
    this.initializeSupplierRequest();
    this.supplierRequest = new SupplierRequest(
      0,  
      new Date(),  
      new Date(), 
      SupplerRequestStatusType.PENDING,  
      new Customer(1, "John Doe", "john@example.com", "1234567890"),  
      new ProfilePackage(),  
      [],  
      new Location()  
    );
  }
  ngOnInit(): void {
    this.showSupplierPendingRequests()
    
  }

   

  initializeSupplierRequest() {
    this.supplierRequest = {
      id: 0, 
      requestDate: new Date(),
      dueDateTime: new Date(),
      requestStatus: SupplerRequestStatusType.PENDING,
      customer: new Customer(1,"","",""), 
      selectedPackage: new ProfilePackage(),
      extraFeatures: [], 
      location: new Location() 
    };
  }

  addSupplierRequest() {
    this.supplierRequestService.addSupplierRequest(this.supplierID, this.supplierRequest).subscribe(
      (response: Supplier) => {
        console.log('Supplier Request Added Successfully', response);
        alert('Supplier Request has been submitted successfully!');
        this.initializeSupplierRequest(); 
      },
      (error) => {
        console.error('Error Adding Supplier Request', error);
        alert('Failed to submit the supplier request.');
      }
    );
  }

  showSupplierPendingRequests(){
    this.supplierRequestService.getAllSupplierRequests().subscribe((listOfSupplierRequests:SupplierRequest[])=>{
      console.log(this.listOfSupplierRequests);
        
      this.listOfSupplierRequests = listOfSupplierRequests
    })
  }
}




