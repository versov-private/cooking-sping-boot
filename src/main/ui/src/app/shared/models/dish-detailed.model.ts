import {User} from "./user.model";
import {RecipeStep} from "./recipe-step.model";
import {Dish} from "./dish.model";
import {Ingredient} from "./ingredient.model";
import {Like} from "./like.model";

export class DishDetailed {
  id: number;
  name: string;
  type: string;
  description: string;
  timeOfCooking: number;
  dateOfCreate: string;
  image: string;
  author: User;
  ingredients: Ingredient[];
  recipeSteps: RecipeStep[];
  numberOfLikes: number;


  constructor(dish: Dish, ingredients: Ingredient[], recipeSteps: RecipeStep[], likes: number) {
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
    this.numberOfLikes = likes;
  }

  convertToDish(): Dish {
    let dish: Dish = new Dish();
    dish.id = 0;
    dish.name = this.name;
    dish.type = this.type;
    dish.description = this.description;
    dish.timeOfCooking = this.timeOfCooking;
    dish.dateOfCreate = this.dateOfCreate;
    dish.image = this.image;
    dish.author = this.author;
    return dish;
  }
}
