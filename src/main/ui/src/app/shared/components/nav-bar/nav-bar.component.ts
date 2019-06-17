import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../services/auth/token-storage.service";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.sass']
})
export class NavBarComponent implements OnInit {

  navBarOpen = false;

  constructor(private tokenService: TokenStorageService) { }

  ngOnInit() {
  }

  toggleNavBar() {
    this.navBarOpen = !this.navBarOpen;
  }

  logOut() {
    this.tokenService.signOut();
    window.location.reload();
  }

}
