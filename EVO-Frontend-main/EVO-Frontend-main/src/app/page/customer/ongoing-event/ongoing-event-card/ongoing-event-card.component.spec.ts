import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OngoingEventCardComponent } from './ongoing-event-card.component';

describe('OngoingEventCardComponent', () => {
  let component: OngoingEventCardComponent;
  let fixture: ComponentFixture<OngoingEventCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OngoingEventCardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OngoingEventCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
