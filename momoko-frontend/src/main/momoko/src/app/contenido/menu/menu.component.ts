import { IndexDataService } from '../index/services/index-data.service';
import { Component, OnInit, AfterViewInit, Inject, PLATFORM_ID, Input, AfterViewChecked, HostListener } from '@angular/core';
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
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  private log = environment.log;
  busqueda = "";
  isLoggedIn = false;

  menu: Menu[];

  scrolled = false;

  constructor(
    private router: Router,
    private authService: AuthService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }

  ngOnInit() {
    this.menu = APP_DATA.menu;

    this.authService.isLoggedIn.subscribe(loginStatus => {
      if (this.log) {
        console.log('is logged In?');
      }   
      this.isLoggedIn = loginStatus;
    });
    this.menu = APP_DATA.menu;
  }

  @HostListener('window:scroll', ['$event']) 
  doSomething(event) {
    if (window.pageYOffset > 0){
      this.scrolled = true;
    } else{
      this.scrolled = false;
    }
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

  openJoinUsModal(){
    $('#bookTemplateModal').modal();
  }

}

