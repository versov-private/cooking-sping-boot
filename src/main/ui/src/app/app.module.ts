import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { UserComponent } from './components/user/user.component';
import { AdminComponent } from './components/admin/admin.component';
import {FormsModule} from "@angular/forms";
import {httpInterceptorProviders} from "./shared/interceptors/auth.interceptor";
import { DishCardComponent } from './shared/components/dish-card/dish-card.component';
import { DishListComponent } from './shared/components/dish-list/dish-list.component';
import { DownloadLinkComponent } from './components/main-page/download-link/download-link.component';
import { FooterComponent } from './shared/components/footer/footer.component';
import { FooterGalleryComponent } from './shared/components/footer-gallery/footer-gallery.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { DishTypeCardComponent } from './components/main-page/dish-type-card/dish-type-card.component';
import { NavBarComponent } from './shared/components/nav-bar/nav-bar.component';
import { ShowAuthDirective } from './shared/directives/show-auth.directive';
import { AddRecipeComponent } from './components/add-recipe/add-recipe.component';
import { SingleRecipeComponent } from './components/single-recipe/single-recipe.component';
import { RecipeComponent } from './shared/components/recipe/recipe.component';
import { StorageComponent } from './components/storage/storage.component';
import {TokenStorageService} from "./shared/services/auth/token-storage.service";
import {AuthService} from "./shared/services/auth/auth.service";
import {RecipeStepService} from "./shared/services/recipe-step.service";
import {ProductService} from "./shared/services/product.service";
import {IngredientService} from "./shared/services/ingredient.service";
import {DishService} from "./shared/services/dish.service";
import {DishFullService} from "./shared/services/dish-full.service";
import { LikeComponent } from './shared/components/like/like.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    UserComponent,
    AdminComponent,
    DishCardComponent,
    DishListComponent,
    DownloadLinkComponent,
    FooterComponent,
    FooterGalleryComponent,
    MainPageComponent,
    DishTypeCardComponent,
    NavBarComponent,
    ShowAuthDirective,
    AddRecipeComponent,
    SingleRecipeComponent,
    RecipeComponent,
    StorageComponent,
    LikeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders, TokenStorageService, AuthService, RecipeStepService, ProductService, IngredientService, DishService, DishFullService],
  bootstrap: [AppComponent]
})
export class AppModule { }
