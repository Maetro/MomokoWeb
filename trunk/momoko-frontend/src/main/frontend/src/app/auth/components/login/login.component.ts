import {Component} from '@angular/core';


import {Router} from '@angular/router';
import { LoginStatus, Login } from 'app/auth/dtos/login';
import { AuthService } from 'app/auth/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  alertStyle = '';
  loginStatus = new LoginStatus('', '');
  model = new Login('', '');

  constructor(private authService: AuthService, private router: Router) {
  }

  onLogin() {
    this.initLogin();
    this.authService.login(this.model)
      .subscribe((status: LoginStatus) => {
        this.loginStatus = status;
        if (status.code === 'FAILURE') {
          this.alertStyle = 'alert alert-danger';
        }
      });
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
