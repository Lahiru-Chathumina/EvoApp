import { Component } from '@angular/core';

@Component({
  selector: 'app-profile-view',
  imports: [],
  templateUrl: './profile-view.component.html',
  styleUrl: './profile-view.component.css'
})
export class ProfileViewComponent {
  
 
  isEditing: boolean = false;
  showPasswordSection: boolean = false;

  customer = {
    fullName: 'Samantha',
    email: 'samantha@example.com',
    phoneNumber: '0785678900',
    address: 'galle',
    gender: 'Male',
    profileImgSrc: '/profile/profile.jpg' 
  };

  constructor() {}

  ngOnInit(): void {}

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      const reader = new FileReader();
      reader.onload = (e) => {
        this.customer.profileImgSrc = e.target?.result as string; 
      };
      reader.readAsDataURL(input.files[0]);
    }
  }

  saveChanges(): void {
   
    this.customer = {
      ...this.customer, 
      fullName: (document.getElementById('editFirstName') as HTMLInputElement).value,
      email: (document.getElementById('editEmail') as HTMLInputElement).value,
      phoneNumber: (document.getElementById('editPhoneNumber') as HTMLInputElement).value,
      address: (document.getElementById('editAddress') as HTMLInputElement).value,
      gender: (document.getElementById('editGender') as HTMLInputElement).value
    };
  }

  toggleEdit(): void {
    this.isEditing = !this.isEditing;
    this.showPasswordSection = this.isEditing; 

    if (!this.isEditing) {
     alert("Profile updated successfully!");
    }
  }
}