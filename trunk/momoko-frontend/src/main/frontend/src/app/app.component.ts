
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { Component, OnInit } from '@angular/core';

import { Libro } from './dtos/libro';
import { LibroService } from './services/libro.service';
import { FileUploadService } from './services/fileUpload.service';

@Component({
  selector: 'app-root',
  template: `
  <div class="content-wrapper">
  <app-menu> Cargando menu... </app-menu>
  <app-header>Cargando cabecera ...</app-header>
  <div class="light-wrapper">
    <div class="container inner">
      <div class="row">
        <div class="col-md-12">
          <router-outlet></router-outlet>
        </div>
      </div>
    </div>
  </div>

</div>
<app-footer></app-footer>`,
  providers: [LibroService, FileUploadService]
})
export class AppComponent implements OnInit {
  constructor() { }

    ngOnInit() {
    }
}

export const API_URL = 'http://localhost:8080/api';
