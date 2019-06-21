import {Component, Input, OnInit} from '@angular/core';
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {LikeService} from "../../services/like.service";
import {Dish} from "../../models/dish.model";

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.sass']
})
export class LikeComponent implements OnInit {

  @Input()
  numberOfLikes: number;

  loggedIn: boolean;

  constructor(private tokenService: TokenStorageService, private likeService: LikeService) { }

  ngOnInit() {
    this.loggedIn = this.tokenService.isLoggedIn();
  }

  toggleLike(dish: Dish) {
    this.likeService.existLike(dish)
  }

}
