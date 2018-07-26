import { Component } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { SignupStatus, NewUser } from '../../dtos/login';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html'
})
export class UserRegistrationComponent {

  private log = environment.log;

  alertStyle = 'alert alert-success';
  signupStatus = new SignupStatus();
  model = new NewUser();
  isDoctor = false;

  constructor(private authService: AuthService) {}

  onSubmit() {
    if (this.isDoctor) {
      this.model.role = 1;
    }
    this.authService.signup(this.model)
      .then((status: SignupStatus) => {
        this.signupStatus.code = status.code;
        this.signupStatus.message = status.message;
        if (this.signupStatus.code === 'USER_ACCOUNT_EXISTS') {
          this.alertStyle = 'alert alert-danger';
        }
      });
    this.alertStyle = 'alert alert-success';
  }
}
