import {User} from "./user.model";
import {Dish} from "./dish.model";

export class Like {
    id: number;
    user: User;
    dish: Dish;
    dateOfCreate: string;


  constructor(dish: Dish, user: User) {
    this.id = 0;
    this.dish = dish;
    this.user = user;
    this.dateOfCreate = Like.getFormattedDate();
  }

  static getFormattedDate(): string{
    let current_datetime = new Date();
    return current_datetime.getFullYear() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getDate() + " " + current_datetime.getHours() + ":" + current_datetime.getMinutes() + ":" + current_datetime.getSeconds();
  }
}
