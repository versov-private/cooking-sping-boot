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
    SingleRecipeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
