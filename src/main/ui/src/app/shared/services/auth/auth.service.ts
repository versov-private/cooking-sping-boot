import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LoginInfo} from "../../models/auth/login-info.model";
import {Observable} from "rxjs";
import {JwtResponse} from "../../models/auth/jwt-response.model";
import {SignUpInfo} from "../../models/auth/sign-up-info.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8080/shared/sign-in';
  private signUpUrl = 'http://localhost:8080/shared/sign-up';

  constructor(private http: HttpClient) { }

  attemptAuth(credentials: LoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signUpUrl, info, httpOptions);
  }
}
