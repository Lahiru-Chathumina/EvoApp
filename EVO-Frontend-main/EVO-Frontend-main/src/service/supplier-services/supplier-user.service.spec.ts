import { TestBed } from '@angular/core/testing';


import { SupplierUserService } from './supplier-user.service';

describe('SupplierUserService', () => {
  let service: SupplierUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SupplierUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
