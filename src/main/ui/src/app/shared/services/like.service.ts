import { Injectable } from '@angular/core';
import {Dish} from "../models/dish.model";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LikeService {

  private readonly UrlShared = "http://localhost:8080/shared/likes/";

  constructor(private http: HttpClient) { }

  findNumberOfLikesByDish(dish: Dish): Observable<number> {
    return this.http.get<number>(this.UrlShared + "dish/" + dish.id);
  }
}
