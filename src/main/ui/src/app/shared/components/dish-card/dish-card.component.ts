import {Component, Input, OnInit} from '@angular/core';
import {Dish} from "../../models/dish.model";

@Component({
  selector: 'app-dish-card',
  templateUrl: './dish-card.component.html',
  styleUrls: ['./dish-card.component.sass']
})
export class DishCardComponent implements OnInit {

  @Input() dish: Dish;

  constructor() { }

  ngOnInit() {
  }

}
