import { TestBed } from '@angular/core/testing';

import { DishFullService } from './dish-full.service';

describe('DishFullService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DishFullService = TestBed.get(DishFullService);
    expect(service).toBeTruthy();
  });
});
