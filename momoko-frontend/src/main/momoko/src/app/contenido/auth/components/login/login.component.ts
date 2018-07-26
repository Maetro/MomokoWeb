import { NewUser, LoginStatus, Login, SignupStatus } from './../../dtos/login';
import { Component, OnInit } from '@angular/core';


import { Router } from '@angular/router';

import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { environment } from '../../../../../environments/environment';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private log = environment.log;

  alertStyle = '';
  loginStatus = new LoginStatus('', '');
  model = new Login('', '');

  nuevoUsuario = new NewUser();
  signupStatus = new SignupStatus();

  user: string;
  authenticated = false;

  constructor(private authService: AuthService, private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    // const headers = new HttpHeaders({
    //   'Content-type': 'application/json',
    //   'Authorization': 'Bearer ' + Cookie.get('access_token')
    // });
    // this.http.get('http://192.168.43.117:5000/login/me', { headers: headers }).subscribe(
    //   (data: StringInfo) => {
    //     if (this.log) {
    //       console.log(data);
    //     }
    //     // Read the result field from the JSON response.
    //     this.user = this.user = data.response;
    //     this.authenticated = true;
    //     this.authService.checkCredentials();
    //   }, err => {
    //     if (this.log) {
    //       console.log('Usuario no autenticado');
    //     }
    //   });

    // this.getLibros();
  }



  onLogin() {
    this.authService.obtainAccessToken(this.model);
    // this.initLogin();
    // this.authService.login(this.model)
    //   .subscribe((status: LoginStatus) => {
    //     this.loginStatus = status;
    //     if (status.code === 'FAILURE') {
    //       this.alertStyle = 'alert alert-danger';
    //     }
    //   });
  }

  onLogout() {
    this.authService.logout();
  }

  private initLogin() {
    this.alertStyle = '';
    this.loginStatus.code = '';
    this.loginStatus.message = '';
  }

  onRegister() {
    this.authService.signup(this.nuevoUsuario)
      .then((status: SignupStatus) => {
        this.signupStatus.code = status.code;
        this.signupStatus.message = status.message;
        if (this.signupStatus.code === 'USER_ACCOUNT_EXISTS') {
          this.alertStyle = 'alert alert-danger';
        }
      });
    this.alertStyle = 'alert alert-success';
  }

  facebookLogin() {
    window.open('http://localhost:5000/oauth2/authorization/facebook', '_blank',
      'height=700,width=700,status=yes,toolbar=no,menubar=no,location=no');
  }

  googleLogin() {
    window.open('http://localhost:5000/oauth2/authorization/google', '_blank',
      'height=700,width=700,status=yes,toolbar=no,menubar=no,location=no');
  }
}
