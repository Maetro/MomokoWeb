import { Component, OnInit } from '@angular/core';

import { Libro } from './libro';
import { LibroService } from './libro.service';

@Component({
  selector: 'app-root',
  template: `
    <h1>{{title}}</h1>
    <h2>Mis libros</h2>
    <ul class="libros">
      <li *ngFor="let libro of libros"
        [class.selected]="libro === selectedLibro"
        (click)="onSelect(libro)">
        <span class="badge">{{libro.libroId}}</span> {{libro.titulo}}
      </li>
    </ul>
    <libro-detail [libro]="selectedLibro"></libro-detail>
  `,
  styles: [`
    .selected {
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
  providers: [LibroService]
})
export class AppComponent implements OnInit {
  title = 'Libros';
  libros: Libro[];
  selectedLibro: Libro;

  constructor(private libroService: LibroService) { }

  getLibros(): void {
    this.libroService.getLibros().then(libros => this.libros = libros);
  }

  ngOnInit(): void {
    this.getLibros();
  }

  onSelect(libro: Libro): void {
    this.selectedLibro = libro;
  }
}
