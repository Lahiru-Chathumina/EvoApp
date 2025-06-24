import { Component } from '@angular/core';
import { ContactService } from '../../../../service/user-services/contactUs.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-contact-us',
  imports: [FormsModule],
  templateUrl: './contact-us.component.html',
  styleUrl: './contact-us.component.css'
})
export class ContactUsComponent {

  name: string = '';
  email: string = '';
  message: string = '';

  constructor(private contactService: ContactService) {}

  submitForm() {
    this.contactService.addContact(this.name, this.email, this.message).subscribe(
      (response) => {
        alert('Contact added successfully!');
        this.name = '';
        this.email = '';
        this.message = '';
      },
      (error) => {
        console.log("error ");
        
      }
    );
  }

}
