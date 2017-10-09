import { AuthService } from 'app/auth/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLoggedIn = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.authService.isLoggedIn.subscribe(loginStatus =>
      this.isLoggedIn = loginStatus
    );
  }

  myHome() {
    if (this.isLoggedIn) {
      const role = Cookie.get('role');
      if (role === 'ROLE_DOCTOR') {
        this.router.navigate(['rx']);
      } else {
        this.router.navigate(['patient/home']);
      }
    } else {
      this.router.navigate(['/']);
    }
  }

  onLogout() {
    this.authService.logout();
  }

}
