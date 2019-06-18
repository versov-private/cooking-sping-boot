import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Dish} from "../models/dish.model";
import {Observable} from "rxjs";
import {RecipeStep} from "../models/recipe-step.model";
import {Ingredient} from "../models/ingredient.model";

@Injectable({
  providedIn: 'root'
})
export class RecipeStepService {

  private readonly UrlShared = "http://localhost:8080/shared/recipe-steps/dish/";
  private readonly Url = "http://localhost:8080/recipe-steps/";

  constructor(private http: HttpClient) { }

  findByDish(dish: Dish):Observable<RecipeStep[]>{
    return this.http.get<RecipeStep[]>(this.UrlShared + dish.id);
  }

  save(recipeStep: RecipeStep): Observable<number> {
    recipeStep.id = 0;
    return this.http.post<number>(this.Url, recipeStep);
  }

}
