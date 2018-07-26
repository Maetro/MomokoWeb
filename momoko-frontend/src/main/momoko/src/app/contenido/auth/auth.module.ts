import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthComponent } from './components/auth/auth.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { AuthRoutingModule } from './auth-app-routing.module';
import { LoginComponent } from './components/login/login.component';
import { UserRegistrationComponent } from './components/user-registration/user-registration.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AuthService } from './services/auth.service';
import { SocialLoginSuccessComponent } from './components/social-login-success/social-login-success.component';


@NgModule({
  imports: [CommonModule, FormsModule, AuthRoutingModule],
  declarations: [AuthComponent, LoginComponent,
    UserRegistrationComponent, ForgotPasswordComponent, LogoutComponent, SocialLoginSuccessComponent],
  providers: [{ provide: AuthService, useClass: AuthService }
  ]
})
export class AuthModule { }