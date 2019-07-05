import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly Url = "http://localhost:8080/users/";

  constructor(private http: HttpClient) { }

  // TODO create controller
  findByUsername(username: string): Observable<User> {
     return this.http.get<User>(this.Url + "username/" + username);
  }
}
