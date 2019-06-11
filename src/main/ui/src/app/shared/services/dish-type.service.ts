import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DishType} from '../models/dishType.model';

@Injectable({
  providedIn: 'root'
})
export class DishTypeService {

  private readonly URL: string = '/assets/json/dish-types.json';

  constructor(private http: HttpClient) { }

  getDishTypes(): Observable<DishType[]> {
    return this.http.get<DishType[]>(this.URL);
  }
}
