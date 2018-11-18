import { Component, OnInit, AfterViewInit, Inject, PLATFORM_ID, Input, AfterViewChecked } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Menu } from '../../dtos/menu';
import { Router } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';
import { APP_DATA } from '../../app-load/app-data';


declare var $: any;

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.css']
})
export class SidebarMenuComponent implements OnInit {


  private log = environment.log;

  busqueda = "";

  isLoggedIn = false;

  maxheight = 1000;

  menu: Menu[];

  constructor(
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }

  ngOnInit() {
    this.menu = APP_DATA.menu;
    if (isPlatformBrowser(this.platformId)) {
      this.maxheight = screen.availHeight;
    }
  }

  buscarResultados() {
    if (this.log) {
      console.log('Buscar: ' + this.busqueda);
    }
    this.router.navigate(['/buscar/' + this.busqueda]);
    this.busqueda = "";
  }

  getMenuIdentifier(cont: number): string{
    return "#menu-" + cont;
  }

  getSubmenuIdentifier(cont: number): string{
    return "menu-" + cont;
  }

  closeMenu(){
    $('.toggle-btn').click();
  }

}

