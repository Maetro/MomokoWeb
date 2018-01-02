import { APP_DATA } from './../app-load/app-data';
import { AuthService } from 'app/auth/services/auth.service';
import { Component, OnInit, AfterViewChecked, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { IndexDataService } from 'app/services/index-data.service';
import { Menu } from 'app/dtos/menu';
import { environment } from 'environments/environment';

declare var $: any;

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit, AfterViewInit {

  private log = environment.log;

  isLoggedIn = false;

  menu: Menu[];

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.authService.checkLoginStatus();
    // this.authService.checkCredentials();
    // this.isLoggedIn = true;
    this.authService.isLoggedIn.subscribe(loginStatus => {
      if (this.log) {
        console.log('is logged In?');
      }
      this.isLoggedIn = loginStatus;
      $('#main-menu').smartmenus();
    });
    this.menu = APP_DATA.menu;
  }

  ngAfterViewInit(): void {
    if (this.log) {
      console.log('Ejecutando JQuery');
    }
    $('#main-menu').smartmenus();
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
