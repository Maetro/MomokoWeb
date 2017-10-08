import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthRoutingModule } from 'app/auth/auth-app-routing.module';
import { AuthComponent } from 'app/auth/components/auth/auth.component';
import { LoginComponent } from 'app/auth/components/login/login.component';
import { UserRegistrationComponent } from 'app/auth/components/user-registration/user-registration.component';
import { ForgotPasswordComponent } from 'app/auth/components/forgot-password/forgot-password.component';
import { LogoutComponent } from 'app/auth/components/logout/logout.component';
import { AuthService } from 'app/auth/services/auth.service';


@NgModule({
  imports: [CommonModule, FormsModule, AuthRoutingModule],
  declarations: [AuthComponent, LoginComponent,
    UserRegistrationComponent, ForgotPasswordComponent, LogoutComponent],
  providers: [{ provide: AuthService, useClass: AuthService }
  ]
})
export class AuthModule { }
