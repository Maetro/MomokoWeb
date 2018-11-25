import { environment } from '../environments/environment';
import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import {
  Router,
  // import as RouterEvent to avoid confusion with the DOM Event
  Event as RouterEvent,
  NavigationStart,
  NavigationEnd,
  NavigationCancel,
  NavigationError
} from '@angular/router';
import { Angulartics2GoogleAnalytics } from 'angulartics2/ga';
import { isPlatformBrowser } from '@angular/common';
import { OnInit } from '@angular/core/src/metadata/lifecycle_hooks';
import { Globals } from './app.globals';

declare var $: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private log = environment.log;

  private urlSeguimiento = environment.seguimientoJS;

  busqueda: string;

  url_seguimiento: string;

  // Sets initial value to true to show loading spinner on first load

  constructor(
    private router: Router,
    //NO ELIMINAR
    angulartics2GoogleAnalytics: Angulartics2GoogleAnalytics,
    @Inject(PLATFORM_ID) private platformId: Object,
    private globals: Globals
  ) {
    router.events.subscribe((event: RouterEvent) => {
      this.navigationInterceptor(event);
    });
  }

  public get g(){
    return this.globals;
  }

  ngOnInit(): void {
    this.url_seguimiento = 'https://momoko.es/files/' + this.urlSeguimiento;
    this.globals
  }

  buscarResultados() {
    console.log(this.busqueda);
    this.router.navigate(['/buscar/' + this.busqueda]);
    this.busqueda = "";
  }

  navigationInterceptor(event: RouterEvent): void {
    if (event instanceof NavigationStart) {
      this.globals.loading = true;
    }
    if (event instanceof NavigationEnd) {
      if (isPlatformBrowser(this.platformId)) {
        // Client only code.
        this.globals.loading = false;
        const distanciaTop = $(document).scrollTop();
        if (this.log) {
          console.log('Distancia top: ' + distanciaTop);
        }
        if (distanciaTop > 500) {
          $('body,html').animate(
            {
              scrollTop: 1
            },
            800
          );
        }
      }
    }

    // Set loading state to false in both of the below events to hide the spinner in case a request fails
    if (event instanceof NavigationCancel) {
      this.globals.loading = false;
    }
    if (event instanceof NavigationError) {
      this.globals.loading = false;
    }
  }
}
