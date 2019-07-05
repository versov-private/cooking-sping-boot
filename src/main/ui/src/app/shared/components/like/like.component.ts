import {Component, Input, OnInit} from '@angular/core';
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {LikeService} from "../../services/like.service";
import {DishDetailed} from "../../models/dish-detailed.model";
import {Like} from "../../models/like.model";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.sass']
})
export class LikeComponent implements OnInit {

  @Input()
  dishDetailed: DishDetailed;
  loggedIn: boolean;
  like: Like;

  constructor(private tokenService: TokenStorageService, private likeService: LikeService, private userService: UserService) { }

  ngOnInit() {
    this.loggedIn = this.tokenService.isLoggedIn();
    if(this.loggedIn) {
      this.userService.findByUsername(this.tokenService.getUsername())
        .subscribe(user => this.likeService.findByDishAndUser(this.dishDetailed.convertToDish(), user)
          .subscribe(like => this.like = like));
    }
  }

  toggleLike() {
    if(this.like == null) {
      this.userService.findByUsername(this.tokenService.getUsername())
        .subscribe(user => this.likeService.save(this.dishDetailed.convertToDish(), user));
    } else {
      this.likeService.delete(this.like);
    }
  }

}
