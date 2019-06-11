import { Component, OnInit } from '@angular/core';
import {Dish} from "../../shared/models/dish.model";
import {DishType} from "../../shared/models/dishType.model";
import {DishService} from "../../shared/services/dish.service";
import {DishTypeService} from "../../shared/services/dish-type.service";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.sass']
})
export class MainPageComponent implements OnInit {
  numberOfDishes: number;
  dishes: Dish[];
  dishTypes: DishType[];

  constructor(private dishService: DishService, private dishTypeService: DishTypeService) { }

  ngOnInit() {
    this.dishService.getDishes().subscribe(data => this.numberOfDishes = data.length  );
    this.dishService.getDishes().subscribe(data => this.dishes = data );
    this.dishTypeService.getDishTypes().subscribe( data => this.dishTypes = data);
  }
}
