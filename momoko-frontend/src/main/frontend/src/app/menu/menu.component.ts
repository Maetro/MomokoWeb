import { AuthService } from 'app/auth/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { IndexDataService } from 'app/services/index-data.service';
import { Menu } from 'app/dtos/menu';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLoggedIn = false;

  menu: Menu[];

  constructor(private indexDataService: IndexDataService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.authService.checkLoginStatus();
    // this.authService.checkCredentials();
    // this.isLoggedIn = true;
    this.authService.isLoggedIn.subscribe(loginStatus => {
      this.isLoggedIn = loginStatus
    });
    this.indexDataService.getIndexData().subscribe(datos => {
      console.log('Init index');
      this.menu = datos.menu;
    },
      error => {
        console.log('Error al recuperar los datos generales ', error);
      });
  }

  myHome() {
    if (this.isLoggedIn) {
      const role = Cookie.get('role');
      if (role === 'ROLE_ADMIN') {
        this.router.navigate(['/']);
      } else {
        this.router.navigate(['/']);
      }
    } else {
      this.router.navigate(['/']);
    }
  }

  onLogout() {
    this.authService.logout();
  }

}
