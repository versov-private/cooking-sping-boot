import {Component, Input, OnInit} from '@angular/core';
import {DishType} from "../../../shared/models/dishType.model";

@Component({
  selector: 'app-dish-type-card',
  templateUrl: './dish-type-card.component.html',
  styleUrls: ['./dish-type-card.component.sass']
})
export class DishTypeCardComponent implements OnInit {

  isBlurred: boolean;
  @Input() dishType: DishType;

  constructor() { }

  ngOnInit() {
    this.isBlurred = false;
  }

  toggleBlur(): void {
    this.isBlurred = !this.isBlurred;
  }
}
