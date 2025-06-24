import { CommonModule } from '@angular/common';
import { Component, OnDestroy } from '@angular/core';
import {FormBuilder, FormGroup,ReactiveFormsModule,Validators,} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import Swal from 'sweetalert2';
import { routes } from '../../../app.routes';
import { event } from 'jquery';

interface Heart {
  id: number;
  size: number;
  x: number;
  speed: number;
  delay: number;
  rotation: number;
  rotationSpeed: number;
}


@Component({
  selector: 'app-annivesary-form',
  imports: [CommonModule, ReactiveFormsModule ],
  templateUrl: './annivesary-form.component.html',
  styleUrl: './annivesary-form.component.css',
})
export class AnnivesaryFormComponent implements OnDestroy {
    anniversaryForm: FormGroup;

  constructor(private fb: FormBuilder , private router:Router) {
    this.anniversaryForm = this.fb.group({
      year: ['', Validators.required],
      wifeName: ['', Validators.required],
      husbandName: ['', Validators.required],
      startDate: ['', Validators.required],
      startTime: ['', Validators.required],
      endTime: ['', Validators.required],
      capacity: ['', Validators.required],
      description: ['']
    });
    this.generateHeart();
    this.toggleHearts();
  }

  setFormData() {
    localStorage.removeItem("FormData");
    localStorage.setItem("FormData", JSON.stringify({
      eventDate: this.anniversaryForm.value.startDate,
      eventType: "ANNIVERSARY",
      year: this.anniversaryForm.value.year,
      wifeName: this.anniversaryForm.value.wifeName,
      husbandName: this.anniversaryForm.value.husbandName,
      startDate: this.anniversaryForm.value.startDate,
      startTime: this.anniversaryForm.value.startTime,
      endTime: this.anniversaryForm.value.endTime,
      headCount: this.anniversaryForm.value.capacity,
      description: this.anniversaryForm.value.description
    }));
  }

  onSubmit() {
    if (this.anniversaryForm.valid) {
      console.log(this.anniversaryForm.value);
      this.setFormData();
         Swal.fire({
              icon: 'success',
              title: 'Booking Added Successful',
              text: ''
            });
            this.router.navigate(["/event/venue-selection"]);
            
    }else{
      Swal.fire({
        icon: 'error',
        title: 'Booking Added Fail',
        text: 'Required fill all fields and Correct Input Detatils'
      });
      
    }
  }

  isActive = false;
  buttonText = 'Start Heart Rain';
  hearts: Heart[] = [];
  private heartCount = 0;
  private maxHearts = 50;
  private intervalId: any = null;
  private timeoutId: any = null;
  animationDuration = 3000;

  toggleHearts() {
    if (this.isActive) {
      this.stopHeartRain();
    } else {
      this.startHeartRain();
    }
  }

  startHeartRain() {
    this.isActive = true;
    this.buttonText = `Heart Rain (${this.animationDuration}s)`;
    this.hearts = [];

    this.intervalId = setInterval(() => {
      if (this.hearts.length < this.maxHearts) {
        this.generateHeart();
      }
    }, 300);

    this.timeoutId = setTimeout(() => {
      this.stopHeartRain();
    }, this.animationDuration * 1000);
  }

  stopHeartRain() {
    this.isActive = false;
    this.buttonText = 'Start Heart Rain';
    clearInterval(this.intervalId);
    clearTimeout(this.timeoutId);
    this.hearts = [];
  }

  generateHeart() {
    const heartId = this.heartCount++;

    const heart: Heart = {
      id: heartId,
      size: this.getRandomNumber(15, 45),
      x: this.getRandomNumber(1, 95),
      speed: this.getRandomNumber(3, 8),
      delay: this.getRandomNumber(0, 500),
      rotation: this.getRandomNumber(0, 360),
      rotationSpeed: this.getRandomNumber(-180, 180) / 100,
    };

    this.hearts.push(heart);

    setTimeout(() => {
      this.hearts = this.hearts.filter((h) => h.id !== heartId);
    }, heart.speed * 1000 + heart.delay + 200);
  }

  getRandomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  ngOnDestroy() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
    if (this.timeoutId) {
      clearTimeout(this.timeoutId);
    }
  }

  handleButtonClick() {
    this.onSubmit();
  }
}
