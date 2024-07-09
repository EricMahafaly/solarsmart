import { TestBed } from '@angular/core/testing';

import { PriseService } from './prise.service';

describe('PriseService', () => {
  let service: PriseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PriseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
