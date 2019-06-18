import {Dish} from "./dish.model";
import {Product} from "./product.model";

export class Ingredient {
  id: number;
  dish: Dish;
  product: Product;
  quantityOfProduct: number;
}
