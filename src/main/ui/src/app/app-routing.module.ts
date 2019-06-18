import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {UserComponent} from "./components/user/user.component";
import {AdminComponent} from "./components/admin/admin.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {MainPageComponent} from "./components/main-page/main-page.component";
import {AddRecipeComponent} from "./components/add-recipe/add-recipe.component";
import {RecipeComponent} from "./shared/components/recipe/recipe.component";
import {StorageComponent} from "./components/storage/storage.component";

const routes: Routes = [

  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'user',
    component: UserComponent
  },
  {
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: RegisterComponent
  },
  {
    path: '',
    component: MainPageComponent,
    pathMatch: 'full'
  },
  {
    path: 'add-recipe',
    component: AddRecipeComponent,
  },
  {
    path: 'recipe/:id',
    component: RecipeComponent,
  },
  {
    path: 'storage',
    component: StorageComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
