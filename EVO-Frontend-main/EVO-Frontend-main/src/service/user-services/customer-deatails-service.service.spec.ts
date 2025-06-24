import { TestBed } from '@angular/core/testing';

import { CustomerDeatailsServiceService } from './customer-deatails-service.service';

describe('CustomerDeatailsServiceService', () => {
  let service: CustomerDeatailsServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerDeatailsServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
