import { Injectable } from '@angular/core';
import {DishService} from "./dish.service";
import {ProductService} from "./product.service";
import {RecipeStepService} from "./recipe-step.service";
import {DishFull} from "../models/dish-full.model";
import {Dish} from "../models/dish.model";
import {Product} from "../models/product.model";
import {RecipeStep} from "../models/recipe-step.model";

@Injectable({
  providedIn: 'root'
})
export class DishFullService {

  private dish: Dish;
  private products: Product[];
  private recipeSteps: RecipeStep[];

  constructor(private dishService: DishService, private productService: ProductService, private recipeStepService: RecipeStepService) { }

  findById(id: number): DishFull {
    this.dishService.findById(id).subscribe(data => this.dish = data );
    this.productService.findByDish(this.dish).subscribe( data => this.products = data);
    this.recipeStepService.findByDish(this.dish).subscribe( data => this.recipeSteps = data);

    return new DishFull(this.dish, this.products, this.recipeSteps);
  }

}
