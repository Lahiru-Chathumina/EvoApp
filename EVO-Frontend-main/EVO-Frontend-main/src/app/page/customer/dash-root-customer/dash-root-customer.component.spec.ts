import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashRootCustomerComponent } from './dash-root-customer.component';

describe('DashRootCustomerComponent', () => {
  let component: DashRootCustomerComponent;
  let fixture: ComponentFixture<DashRootCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashRootCustomerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashRootCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
