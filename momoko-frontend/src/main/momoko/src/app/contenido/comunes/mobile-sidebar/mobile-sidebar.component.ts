import { Component, OnInit, Input } from '@angular/core';
import { LibroSimple } from '../../../dtos/libroSimple';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { Router } from '@angular/router';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-mobile-sidebar',
  templateUrl: './mobile-sidebar.component.html',
  styleUrls: ['./mobile-sidebar.component.css']
})
export class MobileSidebarComponent implements OnInit {
  private log = environment.log;

  @Input() libros: LibroSimple[];

  @Input() tituloSeccionLibros: string;

  constructor() {}

  ngOnInit() {}
}
