
import { Editorial } from './../../../dtos/editorial';
import { Component, OnInit } from '@angular/core';
import { Libro } from './../../../dtos/libro';
import { Genero } from './../../../dtos/genero';
import { Autor } from './../../../dtos/autor';
import { LibroService } from './../../../services/libro.service';
import { FileUploadService } from './../../../services/fileUpload.service';

import { DataTableModule, SharedModule } from 'primeng/primeng';

@Component({
  selector: 'app-lista-libros',
  templateUrl: './lista-libros.component.html',
  styleUrls: ['./lista-libros.component.css']

})
export class ListaLibrosComponent implements OnInit {

  loading: boolean;

  title = 'Libros';
  libros: Libro[];
  generos: Genero[];
  selectedLibro: Libro;

  constructor(private libroService: LibroService, private fileUploadService: FileUploadService) {
    this.libros = [];

  }

  getLibros(): void {
    console.log('service -> getLibros()')
    this.libroService.getLibros().then(libros => {
      libros.forEach(element => {
        this.libros =  [ ...this.libros, element ];
      });

    });
  }

  ngOnInit(): void {
    console.log('ngOnInit Lista libros')
    this.loading = true;
    this.libroService.getLibros().then(librosP => {
      const librosList = librosP
      librosList.forEach(element => {
        this.libros =  [ ...this.libros, element ];
      });
      this.loading = false;
    });
    console.log('TEST');
    // this.getLibros();
  }

  selectLibro(libro: Libro) {
    console.log(libro);
    this.selectedLibro = libro;
  }

  nuevoLibro(): void {
    this.selectedLibro = null;
    const libro = new Libro;
    libro.autores = [];
    const autor = new Autor;
    libro.autores.push(autor);
    libro.generos = [];
    libro.editorial = new Editorial;
    this.selectedLibro = libro;
  }

  volver(): void {
    this.selectedLibro = null;
  }

  actualizarOAnadirLibro(libro: Libro): void {
    this.selectedLibro = null;
    this.libros = [];
    this.getLibros();
    console.log(libro);
  }

}
