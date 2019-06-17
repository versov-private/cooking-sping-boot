import {Client} from "./client.model";
import {Product} from "./product.model";
import {RecipeStep} from "./recipe-step.model";
import {Dish} from "./dish.model";

export class DishFull {
  id: number;
  name: string;
  type: string;
  description: string;
  timeOfCooking: number;
  dateOfCreate: string;
  image: string;
  author: Client;
  ingredients: Product[];
  recipeSteps: RecipeStep[];


  constructor(dish: Dish, ingredients: Product[], recipeSteps: RecipeStep[]) {
    this.id = dish.id;
    this.name = dish.name;
    this.type = dish.type;
    this.description = dish.description;
    this.timeOfCooking = dish.timeOfCooking;
    this.dateOfCreate = dish.dateOfCreate;
    this.image = dish.image;
    this.author = dish.author;
    this.ingredients = ingredients;
    this.recipeSteps = recipeSteps;
  }
}
