import { Component, OnInit } from '@angular/core';


import { Router} from '@angular/router';
import { LoginStatus, Login } from 'app/auth/dtos/login';
import { AuthService } from 'app/auth/services/auth.service';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { StringInfo } from 'app/dtos/string';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  alertStyle = '';
  loginStatus = new LoginStatus('', '');
  model = new Login('', '');

  user: string;
  authenticated = false;

  constructor(private authService: AuthService, private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
      });
    this.http.get('http://localhost:8080/account/me', {headers: headers}).subscribe(
      (data: StringInfo) => {
        console.log(data);
      // Read the result field from the JSON response.
      this.user = this.user = data.response;
      this.authenticated = true;
      this.authService.checkCredentials();
    }, err => {
      console.log('Algo fallo');
    });

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
}
