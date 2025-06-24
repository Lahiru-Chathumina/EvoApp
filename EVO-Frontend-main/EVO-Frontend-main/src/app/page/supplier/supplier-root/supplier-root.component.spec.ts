import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierRootComponent } from './supplier-root.component';
import { RatingReviewsComponent } from '../rating-reviews/rating-reviews.component';

describe('SupplierRootComponent', () => {
  let component: SupplierRootComponent;
  let fixture: ComponentFixture<SupplierRootComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: []
    })
    .compileComponents();

    fixture = TestBed.createComponent(SupplierRootComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
