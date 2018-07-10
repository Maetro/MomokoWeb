import { IndexDataService } from './../../services/index-data.service';
import { Component, OnInit, AfterViewInit, Inject, PLATFORM_ID, Input, AfterViewChecked } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Menu } from '../../dtos/menu';
import { Router } from '@angular/router';
import { APP_DATA } from '../../app-load/app-data';

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.css']
})
export class SidebarMenuComponent implements OnInit {


  private log = environment.log;

  busqueda = "";

  isLoggedIn = false;

  menu: Menu[];

  constructor(
    private router: Router,
    private indexDataService: IndexDataService, 
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }

  ngOnInit() {
    this.menu = APP_DATA.menu;
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

}

