import {Dish} from "./dish.model";

export class RecipeStep {
  id: number;
  numberOfStep: number;
  description: string;
  image: string;
  dish: Dish;
}
