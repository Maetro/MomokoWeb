import { TestBed, inject } from '@angular/core/testing';

import { GeneroService } from './genero.service';

describe('GeneroService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GeneroService]
    });
  });

  it('should be created', inject([GeneroService], (service: GeneroService) => {
    expect(service).toBeTruthy();
  }));
});
