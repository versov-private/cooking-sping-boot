import { Component, OnInit } from '@angular/core';
import {SignUpInfo} from "../../shared/models/auth/sign-up-info.model";
import {AuthService} from "../../shared/services/auth/auth.service";
import {Router} from "@angular/router";
import {TokenStorageService} from "../../shared/services/auth/token-storage.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.sass','../login/login.component.sass']
})
export class RegisterComponent implements OnInit {

  form: any = {};
  signUpInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private tokenService: TokenStorageService, private router: Router) {
    if (this.tokenService.isLoggedIn())
      this.router.navigate(['/']);
  }

  ngOnInit() {}

  onSubmit() {
    this.signUpInfo = new SignUpInfo(
      this.form.firstName,
      this.form.lastName,
      this.form.username,
      this.form.email,
      this.form.password,
      this.form.sex);

    this.authService.signUp(this.signUpInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
