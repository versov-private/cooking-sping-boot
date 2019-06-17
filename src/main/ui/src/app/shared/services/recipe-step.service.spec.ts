import { TestBed } from '@angular/core/testing';

import { RecipeStepService } from './recipe-step.service';

describe('RecipeStepService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RecipeStepService = TestBed.get(RecipeStepService);
    expect(service).toBeTruthy();
  });
});
