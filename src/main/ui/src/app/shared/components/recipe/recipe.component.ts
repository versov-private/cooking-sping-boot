import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DishService} from "../../services/dish.service";
import {Dish} from "../../models/dish.model";
import {DishFullService} from "../../services/dish-full.service";
import {DishDetailed} from "../../models/dish-detailed.model";
import {Observable, of} from "rxjs";
import {IngredientService} from "../../services/ingredient.service";
import {RecipeStepService} from "../../services/recipe-step.service";
import {LikeService} from "../../services/like.service";

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.sass'],
  providers: [DishFullService]
})
export class RecipeComponent implements OnInit {

  dishDetailed: DishDetailed;

  constructor(private route: ActivatedRoute,
              private dishService: DishService,
              private ingredientService: IngredientService,
              private recipeStepService: RecipeStepService,
              private likeService: LikeService) {
  }

  ngOnInit() {
    this.dishService.findById(Number(this.route.snapshot.paramMap.get("id"))).subscribe(dish => {
      this.ingredientService.findByDish(dish).subscribe(ingredients => {
        this.recipeStepService.findByDish(dish).subscribe(recipeSteps => {
          this.likeService.findNumberOfLikesByDish(dish).subscribe(numberOfLikes => {
            this.dishDetailed = new DishDetailed(dish, ingredients, recipeSteps, numberOfLikes);
          })
        })
      })
    });
  }

}
