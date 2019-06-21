import {User} from './user.model';
import {IngredientC} from "./ingredient-c.model";

export class Dish {
  id: number;
  name: string;
  type: string;
  description: string;
  timeOfCooking: number;
  dateOfCreate: string;
  image: string;
  author: User;
  ingredients: IngredientC[];
}
