import { Injectable } from '@angular/core';
import {DishService} from "./dish.service";
import {RecipeStepService} from "./recipe-step.service";
import {DishDetailed} from "../models/dish-detailed.model";
import {Dish} from "../models/dish.model";
import {RecipeStep} from "../models/recipe-step.model";
import {IngredientService} from "./ingredient.service";
import {Ingredient} from "../models/ingredient.model";
import {Observable} from "rxjs";
import {of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DishFullService {

  private dish: Dish;
  private ingredients: Ingredient[];
  private recipeSteps: RecipeStep[];

  constructor(private dishService: DishService, private ingredientService: IngredientService, private recipeStepService: RecipeStepService) { }

  findById(id: number):  Observable<DishDetailed> {
    let dishFull: Observable<DishDetailed>;
    this.dishService.findById(id).subscribe(dish => {
      this.ingredientService.findByDish(dish).subscribe(ingredients => {
        this.recipeStepService.findByDish(dish).subscribe(recipeSteps => {
          dishFull = of(new DishDetailed(dish, ingredients, recipeSteps));
        })
      })
    });
    return
  }

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
