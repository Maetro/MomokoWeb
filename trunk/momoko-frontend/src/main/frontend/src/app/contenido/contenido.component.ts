
import { Component, OnInit } from '@angular/core';

import { Libro } from './../dtos/libro';
import { LibroService } from './../libro.service';

@Component({
  selector: 'app-contenido',
  templateUrl: './contenido.component.html',
  styleUrls: ['./contenido.component.css']
})
export class ContenidoComponent implements OnInit {

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
