import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Dish} from "../models/dish.model";
import {Observable} from "rxjs";
import {RecipeStep} from "../models/recipe-step.model";

@Injectable({
  providedIn: 'root'
})
export class RecipeStepService {

  private readonly URL = "http://localhost:8080/shared/recipe-steps/dish/";

  constructor(private http: HttpClient) { }

  findByDish(dish: Dish):Observable<RecipeStep[]>{
    return this.http.get<RecipeStep[]>(this.URL + dish.id);
  }
}
