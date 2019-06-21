import { Injectable } from '@angular/core';
import {DishService} from "./dish.service";
import {RecipeStepService} from "./recipe-step.service";
import {DishDetailed} from "../models/dish-detailed.model";
import {Dish} from "../models/dish.model";
import {RecipeStep} from "../models/recipe-step.model";
import {IngredientService} from "./ingredient.service";
import {Ingredient} from "../models/ingredient.model";

@Injectable({
  providedIn: 'root'
})
export class DishFullService {

  private dish: Dish;
  private ingredients: Ingredient[];
  private recipeSteps: RecipeStep[];

  constructor(private dishService: DishService, private ingredientService: IngredientService, private recipeStepService: RecipeStepService) { }

  save(dishFull: DishDetailed) {
    this.dish = dishFull.convertToDish();
    this.ingredients = dishFull.ingredients;
    this.recipeSteps = dishFull.recipeSteps;
    this.dishService.save(this.dish).subscribe(data => this.dish.id = data);
    this.ingredients.forEach(ingredient => {
      ingredient.dish = this.dish;
      this.ingredientService.save(ingredient);
    });
    this.recipeSteps.forEach( recipeStep => {
      recipeStep.dish = this.dish;
      this.recipeStepService.save(recipeStep);
    })

  }

}
