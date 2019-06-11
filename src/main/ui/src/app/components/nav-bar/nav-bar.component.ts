import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.sass']
})
export class NavBarComponent implements OnInit {

  isNavBarCollapsed = true;
  navBarOpen = false;

  constructor() { }

  ngOnInit() {
  }

  toggleNavBar() {
    this.navBarOpen = !this.navBarOpen;
  }

}
