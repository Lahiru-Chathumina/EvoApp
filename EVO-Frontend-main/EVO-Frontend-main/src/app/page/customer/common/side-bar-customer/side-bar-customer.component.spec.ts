import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SideBarCustomerComponent } from './side-bar-customer.component';

describe('SideBarCustomerComponent', () => {
  let component: SideBarCustomerComponent;
  let fixture: ComponentFixture<SideBarCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SideBarCustomerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SideBarCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
