import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Dish} from "../models/dish.model";
import {Product} from "../models/product.model";
import {Ingredient} from "../models/ingredient.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  private readonly Url = "http://localhost:8080/ingredients/";
  private readonly UrlShared = "http://localhost:8080/shared/ingredients/";

  constructor(private http: HttpClient) { }

  findById(id: number): Observable<Ingredient> {
    return this.http.get<Ingredient>(this.UrlShared + id);
  }

  findByDish(dish: Dish): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.UrlShared + dish.id);
  }

  save(ingredient: Ingredient):Observable<number> {
    ingredient.id = 0;
    return this.http.post<number>(this.Url, ingredient);
  }
}
