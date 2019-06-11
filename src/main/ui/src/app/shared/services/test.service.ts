import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TestService {

  private userUrl = 'http://localhost:8080/test/user';
  private adminUrl = 'http://localhost:8080/test/admin';
  private commonUrl = 'http://localhost:8080/test/common';

  constructor(private http: HttpClient) { }

  getUserBoard(): Observable<string> {
    return this.http.get<string>(this.userUrl);
  }

  getAdminBoard(): Observable<string> {
    return this.http.get<string>(this.adminUrl);
  }

  getCommonBoard(): Observable<string> {
    return this.http.get<string>(this.commonUrl);
  }
}
