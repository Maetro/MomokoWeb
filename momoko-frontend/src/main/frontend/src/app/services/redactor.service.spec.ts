import { TestBed, inject } from '@angular/core/testing';

import { RedactorService } from './redactor.service';

describe('RedactorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RedactorService]
    });
  });

  it('should be created', inject([RedactorService], (service: RedactorService) => {
    expect(service).toBeTruthy();
  }));
});
