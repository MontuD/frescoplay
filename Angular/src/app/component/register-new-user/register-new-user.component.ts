import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataService } from '../../services/data.service';
import { Observable } from 'rxjs';
// import * as alertify from 'alertify.js';
import { Users } from '../../models/users';

@Component({
  selector: 'app-register-new-user',
  templateUrl: './register-new-user.component.html',
  styleUrls: ['./register-new-user.component.css'],
})
export class RegisterNewUserComponent implements OnInit {
  regNewUser = new Users();
  signupForm: FormGroup;

  emptyUserName = 'You must enter a username';
  minlengthUserName = 'User name must be at least 3 characters long';
  maxlengthUserName = 'Username cannot exceed 20 characters';
  userNamePattern = 'Username should be in alphanumeric only';

  emptyPassword = 'You must enter a password';
  minlengthPassword = 'Password must be at least 8 characters long';
  maxlengthPassword = 'Password cannot exceed 20 characters';
  passwordPattern = 'Pattern does not match';

  mobileErrMsg = 'You must enter a valid mobile number';
  emailErrMsg = 'You must enter a valid Email ID';
  locationErrMsg = 'You must enter the location';

  constructor(private route: Router, private dataService: DataService) {}

  ngOnInit() {
    this.signupForm = new FormGroup({
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(20),
        Validators.pattern('^[A-Za-z0-9]+$'),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(20),
        Validators.pattern('^[A-Za-z0-9!€£@#$%^&*]+$'),
      ]),
      mobile: new FormControl('', [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(10),
        Validators.pattern('^[0-9]+$'),
      ]),
      email: new FormControl('', [Validators.required, Validators.email]),
      location: new FormControl('', [Validators.required]),
    });
  }

  get getFormControls() {
    return this.signupForm.controls;
  }
  get getUsername() {
    return this.signupForm.get('username');
  }
  get getPassword() {
    return this.signupForm.get('password');
  }
  get getMobile() {
    return this.signupForm.get('mobile');
  }
  get getEmail() {
    return this.signupForm.get('email');
  }
  get getLocation() {
    return this.signupForm.get('location');
  }

  signUp() {
    // call regNewUser method to perform signup operation
    // if success, redirect to login page
    // else display appropriate error message
    // reset the form

    let userObject = {};
    userObject["user_name"] = this.getUsername.value;
    userObject["password"] = this.getPassword.value;
    userObject["user_mobile"] = this.getMobile.value;
    userObject["user_email"] = this.getEmail.value;
    userObject["location"] = this.getLocation.value;
    console.log(userObject);
    this.regNewUser = new Users(userObject);





  }

  goBack() {
    // should navigate to login page
    this.route.navigate(["login"])
  }
}
