import { TestBed } from '@angular/core/testing';

import { CustomerLoginServiceService } from './customer-login-service.service';

describe('CustomerLoginServiceService', () => {
  let service: CustomerLoginServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerLoginServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
