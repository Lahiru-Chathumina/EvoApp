import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentsCustomerComponent } from './payments-customer.component';

describe('PaymentsCustomerComponent', () => {
  let component: PaymentsCustomerComponent;
  let fixture: ComponentFixture<PaymentsCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PaymentsCustomerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentsCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
