import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DishTypeCardComponent } from './dish-type-card.component';

describe('DishTypeCardComponent', () => {
  let component: DishTypeCardComponent;
  let fixture: ComponentFixture<DishTypeCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DishTypeCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DishTypeCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
