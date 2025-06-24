import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierPendingRequestComponent } from './supplier-pending-request.component';

describe('SupplierPendingRequestComponent', () => {
  let component: SupplierPendingRequestComponent;
  let fixture: ComponentFixture<SupplierPendingRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SupplierPendingRequestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SupplierPendingRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
