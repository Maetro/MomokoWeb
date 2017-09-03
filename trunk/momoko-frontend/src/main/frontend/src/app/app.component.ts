
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { Component, OnInit } from '@angular/core';

import { Libro } from './dtos/libro';
import { LibroService } from './libro.service';
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
      <div class="row">
        <div class="col-md-12">
          <div>
            Usuarios: <a href="http://localhost:8080/usuario/usuarios">click here</a>
          </div>
          <div>
            Libros: <a href="http://localhost:8080/modelo/libros">click here</a>
          </div>
          <div>
            Generos: <a href="http://localhost:8080/modelo/generos">click here</a>
          </div>
          <div>
            With Facebook: <a href="http://localhost:8080/login/facebook">click here</a>
          </div>
          <div>
            With Gmail: <a href="http://localhost:8080/login/gmail">click here</a>
          </div>
        </div>
        <!-- <app-contenido>Cargando libros...</app-contenido> -->
      </div>
    </div>
  </div>

</div>
<app-footer></app-footer>`,
  styles: [
  ` .selected {
      background-color: #CFD8DC !important;
      color: white;
    }
    .libros {
      margin: 0 0 2em 0;
      list-style-type: none;
      padding: 0;
      width: 15em;
    }
    .libros li {
      cursor: pointer;
      position: relative;
      left: 0;
      background-color: #EEE;
      margin: .5em;
      padding: .3em 0;
      height: 1.6em;
      border-radius: 4px;
      box-sizing: content-box;
    }
    .libros li.selected:hover {
      background-color: #BBD8DC !important;
      color: white;
    }
    .libros li:hover {
      color: #607D8B;
      background-color: #DDD;
      left: .1em;
    }
    .libros .text {
      position: relative;
      top: -3px;
    }
    .libros .badge {
      display: inline-block;
      font-size: small;
      color: white;
      padding: 0.8em 0.7em 0 0.7em;
      background-color: #607D8B;
      line-height: 1em;
      position: relative;
      left: -1px;
      top: -4px;
      height: 1.8em;
      margin-right: .8em;
      border-radius: 4px 0 0 4px;
    }
  `],
  providers: [LibroService, FileUploadService]
})
export class AppComponent implements OnInit {
  constructor() { }

    ngOnInit() {
    }
}

export const API_URL = 'http://localhost:8080/api';
