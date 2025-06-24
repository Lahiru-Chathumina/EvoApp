import { CommonModule, NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { HttpClient } from '@angular/common/http';
import { CustomerDeatailsServiceService } from '../../../../../../../service/user-services/customer-deatails-service.service';


interface AuditHistory {
  createdDate: Date;
  lastModified: Date;
}

type AdminType = 'SUPER_ADMIN' | 'REGULAR_ADMIN';

interface Admin {
  adminId: number;
  auditHistory: AuditHistory;
  type: AdminType;
}

interface Customer {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  contactNumber: string;
  profileImageUrl: string;
  address: string;
}

@Component({
  selector: 'app-admin-user-management',
  imports: [CommonModule,FormsModule,],
  templateUrl: './admin-user-management.component.html',
  styleUrl: './admin-user-management.component.css'
})
export class AdminUserManagementComponent implements OnInit {

// public id :String="1"
  

    constructor(private customerdetails:CustomerDeatailsServiceService,private fb:FormsModule,private http:HttpClient){

    }

  ngOnInit(): void {
      this.loadUser();
    this.getAllSupplier();
  }


    public userList: any=[]
    public supplierList: any=[]



    loadUser(){

      this.customerdetails.getAll().subscribe({
        next:(res)=>{
       this.userList=res;
      console.log(this.userList);
           
        }
      })

    }

handleImgError(event: Event): void {
  const target = event.target as HTMLImageElement;
  target.src = 'https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png';
}


getAllSupplier(){
  this.customerdetails.getAllSupllier().subscribe({
    next:(res)=>{
      this.supplierList=res;
      
      console.log(this.supplierList);
      


    }
  })
}

deleteUser(id:number){
  this.customerdetails.Delete(id).subscribe({
    next:(res)=>{
      
      console.log(res);
  }})
  this.loadUser();
}


deleteSupllier(id :number){
  this.customerdetails.deleteSuplier(id).subscribe({
    next:(res)=>{
      this.getAllSupplier();
      console.log(res);
      
    }
  })
}

showSupplierPopup = false;
selectedSupplier: any = {};

openSupplierUpdatePopup(supplier: any) {
  this.selectedSupplier = { ...supplier }; 
  this.showSupplierPopup = true;
}

closeSupplierPopup() {
  this.showSupplierPopup = false;
  this.selectedSupplier = {};
}



showUserPopup = false;
selectedUser: any = {};

openUserUpdatePopup(user: any) {
  this.selectedUser = { ...user }; 
  this.showUserPopup = true;
}

closeUserPopup() {
  this.showUserPopup = false;
  this.selectedUser = {};
}






validateUpdateCustomer(id:number): void {
  if (

    !this.selectedUser.id ||
    !this.selectedUser.firstName ||
    !this.selectedUser.lastName ||
    !this.selectedUser.email ||
    !this.selectedUser.contactNumber ||
    !this.selectedUser.address
  ) {
    alert('Please fill in all fields.');
    return;



  }

  this.http.put("http://localhost:8080/customer/update/" + id, this.selectedUser).subscribe({
    next:(res)=>{
      console.log(res);
      this.loadUser();
      
      this.closeUserPopup();
       this.loadUser();
    },
    error:(err)=>{
      console.log(err);
    }
  })
 
}

public supid:string=''

  validateUpdateSupplier(id:number): void {
    if (
      !this.selectedSupplier.businessName ||
      !this.selectedSupplier.businessContactNumber ||
      !this.selectedSupplier.businessEmail ||
      !this.selectedSupplier.description ||
      !this.selectedSupplier.availability||
      !this.selectedSupplier.category
    ) {
      alert('Please fill in all fields.');
      return;
    }

    this.http.put("http://localhost:8080/api/supplier/update/" + id, this.selectedSupplier).subscribe({
      next:(res)=>{
        console.log(res);
        this.getAllSupplier();
        this.closeSupplierPopup();
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }
}
