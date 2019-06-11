import { Injectable } from '@angular/core';
import {Dish} from '../models/dish.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DishService {

  private readonly URL: string = '/assets/json/dishes.json';

  constructor(private http: HttpClient) {
  }

  public getDishes(): Observable<Dish[]> {
    return this.http.get<Dish[]>(this.URL);
  }

}
