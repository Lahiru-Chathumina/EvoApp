import { NgFor } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-rating-reviews',
  imports: [NgFor],
  templateUrl: './rating-reviews.component.html',
  styleUrl: './rating-reviews.component.css'
})
export class RatingReviewsComponent {
ratingStats = {
  growth: '10.0K',
  average: 4.0,
  range: 'December 2024 - February 2025',
  distribution: [
    { stars: 5, count: 2000, width: '60%', color: 'rgb(127, 255, 212)' },
    { stars: 4, count: 1000, width: '30%', color: 'rgb(208, 118, 224)' },
    { stars: 3, count: 500, width: '20%', color: 'yellow' },
    { stars: 2, count: 200, width: '5%', color: 'blue' },
    { stars: 1, count: 0, width: '0%', color: 'red' },
  ]
};

reviewsData = [
  {
    name: 'Sarah Thompson',
    date: '12-09-2024',
    rating: 5,
    comment: `Kevin's photos of our family reunion were fantastic! He has a knack for capturing genuine moments and made everyone feel at ease. We're thrilled with the results!`,
    profile: '/Rating-Reviews-img/profile.jpg'
  },
  {
    name: 'Emily Rodriguez',
    date: '15-11-2024',
    rating: 5,
    comment: `Absolutely delighted with Kevin's photography! His ability to capture the essence of our family event was remarkable.`,
    profile: 'https://images.unsplash.com/photo-1639149888905-fb39731f2e6c?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fHVzZXIlMjBwcm9maWxlfGVufDB8fDB8fHww'
  },
  {
    name: 'Michael Chen',
    date: '20-12-2024',
    rating: 5,
    comment: `Kevin's photographic skills are truly exceptional. From wedding to family portraits, his ability to tell a story through images is unparalleled.`,
    profile: 'https://images.unsplash.com/photo-1678286742832-26543bb49959?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8dXNlciUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D'
  },
  {
    name: 'David Kim',
    date: '05-01-2025',
    rating: 5,
    comment: `Professional, creative, and absolutely brilliant. Kevin transformed our corporate event photos into storytelling masterpieces.`,
    profile: 'data:image/jpeg;base64,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'
  }
];

}
