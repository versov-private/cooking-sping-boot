import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {UserComponent} from "./components/user/user.component";
import {AdminComponent} from "./components/admin/admin.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";

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
    redirectTo: 'home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
