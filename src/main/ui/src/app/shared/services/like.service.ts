import { Injectable } from '@angular/core';
import {Dish} from "../models/dish.model";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageService} from "./auth/token-storage.service";
import {Like} from "../models/like.model";
import {User} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class LikeService {

  private readonly UrlShared = "http://localhost:8080/shared/likes/";
  private readonly Url = "http://localhost:8080/likes/";

  constructor(private http: HttpClient, private tokenService: TokenStorageService) { }

  findByDishAndUser(dish: Dish, user: User): Observable<Like> {
    return this.http.get<Like>(this.Url + "dish/" + dish.id + "/user/" + user.id);
  }

  findNumberOfLikesByDish(dish: Dish): Observable<number> {
    return this.http.get<number>(this.UrlShared + "dish/" + dish.id);
  }

  //TODO Add controller for this
  existLike(dish: Dish): Observable<boolean> {
    return this.http.get<boolean>(this.Url + "dish/" + dish.id + "/user/" + this.tokenService.getUsername());
  }

  save(dish: Dish, user: User): Observable<Like> {
    return this.http.post<Like>(this.Url, new Like(dish, user));
  }

  delete(like: Like) {
    this.http.delete(this.Url + like.id);
  }

}
