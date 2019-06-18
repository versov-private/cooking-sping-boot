import { Injectable } from '@angular/core';
import {Dish} from '../models/dish.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DishService {

  // private readonly URL: string = 'http://localhost:8080/shared/dishes/';
  private readonly URL: string = '/assets/json/dishes.json';
  private readonly UrlCrude: string = "http://localhost:8080/dishes/";

  constructor(private http: HttpClient) {
  }

  public getDishes(): Observable<Dish[]> {
    return this.http.get<Dish[]>(this.URL);
  }

  public findById(id: number): Observable<Dish> {
    return this.http.get<Dish>(this.URL + id);
  }

  public save(dish: Dish): Observable<number> {
    return this.http.post<number>(this.UrlCrude, dish);
  }

}
