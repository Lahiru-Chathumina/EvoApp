import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpSummaryComponent } from './pop-up-summary.component';

describe('PopUpSummaryComponent', () => {
  let component: PopUpSummaryComponent;
  let fixture: ComponentFixture<PopUpSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PopUpSummaryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopUpSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
