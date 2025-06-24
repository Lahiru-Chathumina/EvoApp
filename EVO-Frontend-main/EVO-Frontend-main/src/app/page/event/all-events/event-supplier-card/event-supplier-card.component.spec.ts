import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventSupplierCardComponent } from './event-supplier-card.component';

describe('EventSupplierCardComponent', () => {
  let component: EventSupplierCardComponent;
  let fixture: ComponentFixture<EventSupplierCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EventSupplierCardComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(EventSupplierCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
