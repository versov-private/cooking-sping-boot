import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../shared/services/auth/token-storage.service";

interface Info {
  token: string;
  username: string;
  authorities: string[];

}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  info: Info;

  constructor(private token: TokenStorageService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    }
  }

  logOut() {
    this.token.signOut();
    window.location.reload();
  }

  isLoggedIn(): boolean{
    return this.info.token != null && this.info.authorities != null && this.info.username != null;
  }

}
