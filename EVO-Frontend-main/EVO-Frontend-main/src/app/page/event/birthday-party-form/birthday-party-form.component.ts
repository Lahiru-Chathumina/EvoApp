import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { event } from 'jquery';
import Swal from 'sweetalert2';


interface birthdayParty {
  ownerName: string;
  eventdate: string | null;
  headcount: number;
  starttime: Date;
  endtime: Date;
  formdata: FormData;

}
@Component({
  selector: 'app-birthday-party-form',
  imports: [ FormsModule],
  templateUrl: './birthday-party-form.component.html',
  styleUrl: './birthday-party-form.component.css'
})



export class BirthdayPartyFormComponent {

  public birthdayPartyForm: birthdayParty = {
    ownerName: '',
    eventdate: null,
    headcount: 0,
    starttime: new Date(),
    endtime: new Date(),
    formdata: new FormData()
  };

  formdata: FormData = new FormData();

  constructor(private router: Router) { 

  }

  submitbuttonOnClick() {
    if (!this.birthdayPartyForm.ownerName) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Owner Name is required!',
      });
      return;
    }
    
    if (!this.birthdayPartyForm.eventdate) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Event Date is required!',
      });
      return;
    }
    if (Date.parse(this.birthdayPartyForm.eventdate) < Date.now()) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Please select a date in future!',
      });
      return;
    }
    if (this.birthdayPartyForm.headcount <= 0) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Headcount should be greater than 0!',
      });
      return;
    }
    if (!this.birthdayPartyForm.starttime || !this.birthdayPartyForm.endtime) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Start Time and End Time are required!',
      });
      return;
    }
    if (this.birthdayPartyForm.starttime >= this.birthdayPartyForm.endtime) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Start Time must be earlier than End Time!',
      });
      return;
    }

    this.formdata.append('ownerName', this.birthdayPartyForm.ownerName);
    this.formdata.append('eventdate', this.birthdayPartyForm.eventdate.toString());
    this.formdata.append('headcount', this.birthdayPartyForm.headcount.toString());
    this.formdata.append('starttime', this.birthdayPartyForm.starttime.toString());
    this.formdata.append('endtime', this.birthdayPartyForm.endtime.toString());

    Swal.fire({
      icon: 'success',
      title: 'Form submitted successfully!',
      showConfirmButton: false,
      timer: 1500,
    }); 
    
    console.log(this.birthdayPartyForm);
    localStorage.removeItem('FormData');
    localStorage.setItem('FormData', JSON.stringify({
      eventType: 'BIRTHDAY_PARTY',
      ownerName: this.birthdayPartyForm.ownerName,
      eventDate: this.birthdayPartyForm.eventdate,
      headCount: this.birthdayPartyForm.headcount,
      startTime: this.birthdayPartyForm.starttime,
      endTime: this.birthdayPartyForm.endtime,
    }));
    
    this.router.navigate(['/event/venue-selection']);
  }
}
