import { TestBed, inject } from '@angular/core/testing';

import { JsonAdapterService } from './json-adapter.service';

describe('JsonAdapterService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [JsonAdapterService]
    });
  });

  it('should be created', inject([JsonAdapterService], (service: JsonAdapterService) => {
    expect(service).toBeTruthy();
  }));
});
