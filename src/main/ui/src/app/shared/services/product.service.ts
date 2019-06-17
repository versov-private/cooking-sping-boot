import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Dish} from "../models/dish.model";
import {Observable} from "rxjs";
import {Product} from "../models/product.model";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private readonly URL = "http://localhost:8080/shared/products/dish/";

  constructor(private http: HttpClient) { }

  findByDish(dish: Dish): Observable<Product[]>{
    return this.http.get<Product[]>(this.URL + dish.id);
  }
}
