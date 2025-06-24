import { Component } from '@angular/core';
import { RatingReviewsComponent } from "../rating-reviews/rating-reviews.component";
import { DetailedPreviousWorkComponent } from "../detailed-previous-work/detailed-previous-work.component";

@Component({
  selector: 'app-beauty-essentials-info',
  imports: [RatingReviewsComponent, DetailedPreviousWorkComponent],
  templateUrl: './beauty-essentials-info.component.html',
  styleUrl: './beauty-essentials-info.component.css'
})
export class BeautyEssentialsInfoComponent {

}
