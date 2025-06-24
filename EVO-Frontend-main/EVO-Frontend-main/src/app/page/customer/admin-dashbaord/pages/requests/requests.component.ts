import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-requests',
  imports: [CommonModule],
  templateUrl: './requests.component.html',
  styleUrl: './requests.component.css'
})
export class RequestsComponent {
  requests = [
    { date: '2022/4/5',name:'Sadun Fernando', type:'Catering',email:'sandunfernando@gmail.com',contact:'0777777553',image:'https://img.freepik.com/free-photo/portrait-smiling-young-man-looking-camera_23-2148148708.jpg?ga=GA1.1.1465633965.1733474789&semt=ais_hybrid' },
    { date: '2022/4/6',name:'Kasun Perera',type:'Photographer',email:'kasun2002@gmail.com',contact:'0754677553',image:'https://img.freepik.com/premium-photo/beautiful-winter-portrait-handsome-man-with-hairstyle_338491-2278.jpg?w=826'  },
    { date: '2022/4/7',name:'Naduni Kavindya',type:'Beauty Salon',email:'nadunikavi@gmail.com',contact:'0777769553',image:'https://img.freepik.com/free-photo/young-woman-portrait-street_1303-13672.jpg?ga=GA1.1.1465633965.1733474789&semt=ais_hybrid' }
  ];

  selectedRequest: any = null;

  openModal(request: any) {
    this.selectedRequest = request;
  }

  closeModal() {
    this.selectedRequest = null;
  }
popUpDetails() {
    Swal.fire({
      title: "Supplier Details",
      text: "All the details of the Supplier"
    });
  }
  approveRequest(request: any) {
    Swal.fire({
      title: "Confirmation",
      text: "Your Request Approved...",
      icon: "success",
      confirmButtonText: "OK"
    }).then((result) => {
      if (result.isConfirmed) {
        this.requests = this.requests.filter(r => r !== request);
      }
    });
  }
  
  rejectRequest(request: any) {
    Swal.fire({
      title: "Reject",
      text: "Your Request Rejected...",
      icon:"error",
      confirmButtonText:"ok"
    }).then((result)=>{
      if(result.isConfirmed){
        this.requests=this.requests.filter(r=> r !==request);
      
      }
    });
  }
  removeRequest(request: any) {
    this.requests = this.requests.filter(r => r !== request);
  }

  
  }
  






