import { TestBed } from '@angular/core/testing';

import { DishTypeService } from './dish-type.service';

describe('DishTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DishTypeService = TestBed.get(DishTypeService);
    expect(service).toBeTruthy();
  });
});
