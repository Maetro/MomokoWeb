import { Component } from '@angular/core';

import { Login } from 'app/auth/dtos/login';

@Component({
  templateUrl: './forgot-password.component.html'
})
export class ForgotPasswordComponent {

  model = new Login('', '');

  onSubmit() {
    console.log('To be implemented');
  }
}
