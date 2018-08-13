import { IndexDataService } from '../../services/index-data.service';
import { Component, OnInit, AfterViewInit, Inject, PLATFORM_ID, Input, AfterViewChecked } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Menu } from '../../dtos/menu';
import { Router } from '@angular/router';
import { APP_DATA } from '../../app-load/app-data';
import { isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth/services/auth.service';

declare var $: any;

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {


  private log = environment.log;

  busqueda = "";

  isLoggedIn = false;

  menu: Menu[];

  constructor(
    private router: Router,
    private authService: AuthService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }

  ngOnInit() {
    this.menu = APP_DATA.menu;
    this.authService.checkLoginStatus();

    this.authService.isLoggedIn.subscribe(loginStatus => {
      if (this.log) {
        console.log('is logged In?');
      }
      this.isLoggedIn = loginStatus;
    });
    this.menu = APP_DATA.menu;
    this.authService.checkCredentials();
  }

  buscarResultados() {
    if (this.log) {
      console.log('Buscar: ' + this.busqueda);
    }
    this.router.navigate(['/buscar/' + this.busqueda]);
    this.busqueda = "";
  }

  onLogout() {
    this.authService.logout();
    this.authService.checkCredentials();
  }


}

