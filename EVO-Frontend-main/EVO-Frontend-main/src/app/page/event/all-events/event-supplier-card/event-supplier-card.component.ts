import { Component, Input, OnInit, AfterViewInit } from '@angular/core';
import { v4 as uuidv4 } from 'uuid';
import { NgFor, NgIf } from '@angular/common';


@Component({
  selector: 'app-event-supplier-card',
  imports: [NgFor],
  templateUrl: './event-supplier-card.component.html',
  styleUrl: './event-supplier-card.component.css'
})
export class EventSupplierCardComponent implements OnInit, AfterViewInit {
  @Input() suppliers: any[] = [];
  carouselId: string = '';

  constructor() { }

  ngOnInit() {
    // Generate a unique ID for each carousel
    this.carouselId = 'carousel-' + uuidv4();
  }

  ngAfterViewInit() {
    // Ensure Bootstrap carousel is properly initialized for each instance
    this.initializeCarousel();
  }

  private initializeCarousel() {
    // Ensure Bootstrap's Carousel is available
    if ((window as any).bootstrap && (window as any).bootstrap.Carousel) {
      const carouselElement = document.getElementById(this.carouselId);
      if (carouselElement) {
        new (window as any).bootstrap.Carousel(carouselElement, {
          ride: false,
          interval: false
        });
      }
    }
  }
}