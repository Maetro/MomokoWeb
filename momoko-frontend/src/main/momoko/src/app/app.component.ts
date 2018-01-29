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
import { environment } from '../environments/environment';
import { isPlatformBrowser } from '@angular/common';

declare var $: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  private log = environment.log;

  // Sets initial value to true to show loading spinner on first load
  loading = true

  constructor(
    private router: Router,
    angulartics2GoogleAnalytics: Angulartics2GoogleAnalytics,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    router.events.subscribe((event: RouterEvent) => {
      this.navigationInterceptor(event)
    })
  }

  navigationInterceptor(event: RouterEvent): void {
    if (event instanceof NavigationStart) {
      this.loading = true
    }
    if (event instanceof NavigationEnd) {
      if (isPlatformBrowser(this.platformId)) {
        // Client only code.
        this.loading = false;
        const distanciaTop = $(document).scrollTop();
        if (this.log) {
          console.log('Distancia top: ' + distanciaTop);
        }
        if (distanciaTop > 500) {

          $('body,html').animate({
            scrollTop: 0
          }, 800);
        }
      }

    }

    // Set loading state to false in both of the below events to hide the spinner in case a request fails
    if (event instanceof NavigationCancel) {
      this.loading = false
    }
    if (event instanceof NavigationError) {
      this.loading = false
    }
  }
}
