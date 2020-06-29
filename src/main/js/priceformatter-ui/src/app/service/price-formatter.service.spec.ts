import { TestBed } from '@angular/core/testing';

import { PriceFormatterService } from './price-formatter.service';

describe('PriceFormatterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PriceFormatterService = TestBed.get(PriceFormatterService);
    expect(service).toBeTruthy();
  });
});
