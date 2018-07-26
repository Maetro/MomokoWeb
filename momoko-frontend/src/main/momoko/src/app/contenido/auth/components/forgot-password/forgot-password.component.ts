import { Component } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { Login } from '../../dtos/login';


@Component({
  templateUrl: './forgot-password.component.html'
})
export class ForgotPasswordComponent {

  private log = environment.log;

  model = new Login('', '');

  onSubmit() {
    if (this.log) {
      console.log('To be implemented');
    }
  }
}
