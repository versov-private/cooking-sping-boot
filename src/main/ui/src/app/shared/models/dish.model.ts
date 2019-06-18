import {Client} from './client.model';
import {IngredientC} from "./ingredient-c.model";

export class Dish {
  id: number;
  name: string;
  type: string;
  description: string;
  timeOfCooking: number;
  dateOfCreate: string;
  image: string;
  author: Client;
  ingredients: IngredientC[];
}
