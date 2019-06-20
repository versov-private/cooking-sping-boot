import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.sass']
})
export class LikeComponent implements OnInit {

  @Input()
  numberOfLikes: number;

  constructor() { }

  ngOnInit() {
  }

}
