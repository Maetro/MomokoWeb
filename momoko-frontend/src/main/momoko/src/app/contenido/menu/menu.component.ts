import { Component, OnInit, AfterViewInit, Inject, PLATFORM_ID, Input } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Menu } from '../../dtos/menu';
import { Router } from '@angular/router';
import { APP_DATA } from '../../app-load/app-data';
import { isPlatformBrowser } from '@angular/common';
import { FormsModule }   from '@angular/forms';

declare var $: any;

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit, AfterViewInit {

  private log = environment.log;

  busqueda = "";

  isLoggedIn = false;

  menu: Menu[];

  constructor(
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }

  ngOnInit() {
    this.menu = APP_DATA.menu;
  }

  ngAfterViewInit(): void {
    if (this.log) {
      console.log('Ejecutando JQuery');
    }
    if (isPlatformBrowser(this.platformId)) {
      // Client only code.
      $('#main-menu').smartmenus();
   }
    
  }

  buscarResultados(){
    if (this.log) {
      console.log('Buscar: ' + this.busqueda);
    }
    this.router.navigate(['/buscar/' + this.busqueda]);
  }


}