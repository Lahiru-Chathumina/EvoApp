import { TestBed } from '@angular/core/testing';

import { YourChatBortService } from './your-chat-bort.service';

describe('YourChatBortService', () => {
  let service: YourChatBortService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(YourChatBortService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
