import { GeneralDataService } from 'app/services/general-data.service';

import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { Component, OnInit, APP_INITIALIZER } from '@angular/core';

import { Libro } from './dtos/libro';
import { LibroService } from './services/libro.service';
import { FileUploadService } from './services/fileUpload.service';
import {
  Router,
  // import as RouterEvent to avoid confusion with the DOM Event
  Event as RouterEvent,
  NavigationStart,
  NavigationEnd,
  NavigationCancel,
  NavigationError
} from '@angular/router'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [LibroService, FileUploadService, GeneralDataService]
})
export class AppComponent{

  // Sets initial value to true to show loading spinner on first load
  loading = true

  constructor(private router: Router) {
    router.events.subscribe((event: RouterEvent) => {
      this.navigationInterceptor(event)
    })
  }

  navigationInterceptor(event: RouterEvent): void {
    if (event instanceof NavigationStart) {
      this.loading = true
    }
    if (event instanceof NavigationEnd) {
      this.loading = false
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

export const API_URL = 'http://localhost:8080/api';
