import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DishService} from "../../services/dish.service";
import {Dish} from "../../models/dish.model";

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.sass']
})
export class RecipeComponent implements OnInit {

  dish: Dish;

  constructor(private route: ActivatedRoute, private dishService: DishService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.dishService.getDishes().subscribe(dishes => dishes.forEach(dish => {
        // @ts-ignore
        if (window.location.href[window.location.href.length-1] == dish.id) {
          this.dish = dish;
        }
      }))
      ;
    });
  }

}
