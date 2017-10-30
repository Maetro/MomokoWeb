import { LibroSimple } from './../../../dtos/libroSimple';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-libros-mes-sidebar',
  templateUrl: './libros-mes-sidebar.component.html'
})
export class LibrosMesSidebarComponent implements OnInit {

  @Input() libros: LibroSimple;

  constructor() { }

  ngOnInit() {
  }

}
