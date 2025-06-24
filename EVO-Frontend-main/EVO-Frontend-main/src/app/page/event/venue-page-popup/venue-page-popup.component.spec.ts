import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VenuePagePopupComponent } from './venue-page-popup.component';

describe('VenuePagePopupComponent', () => {
  let component: VenuePagePopupComponent;
  let fixture: ComponentFixture<VenuePagePopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VenuePagePopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VenuePagePopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
