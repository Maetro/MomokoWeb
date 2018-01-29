
import { Editorial } from './../../../dtos/editorial';
import { Component, OnInit } from '@angular/core';
import { Libro } from './../../../dtos/libro';
import { Genero } from './../../../dtos/genero';
import { Autor } from './../../../dtos/autor';
import { LibroService } from './../../../services/libro.service';
import { FileUploadService } from './../../../services/fileUpload.service';

import { DataTableModule, SharedModule } from 'primeng/primeng';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-lista-libros',
  templateUrl: './lista-libros.component.html',
  styleUrls: ['./lista-libros.component.css']

})
export class ListaLibrosComponent implements OnInit {

  private log = environment.log;

  loading: boolean;

  title = 'Libros';
  libros: Libro[];
  generos: Genero[];
  selectedLibro: Libro;

  constructor(private libroService: LibroService, private fileUploadService: FileUploadService) {
    this.libros = [];

  }

  getLibros(): void {
    this.libroService.getLibros().subscribe(libros => {
      libros.forEach(element => {
        this.libros = [...this.libros, element];
      });

    });
  }

  ngOnInit(): void {
    this.loading = true;
    this.libroService.getLibros().subscribe(librosP => {
      const librosList = librosP
      librosList.forEach(element => {
        this.libros = [...this.libros, element];
      });
      this.loading = false;
    });
  }

  selectLibro(libro: Libro) {
    if (this.log) {
      console.log(libro);
    }
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
    if (this.log) { console.log(libro); }
    this.selectedLibro = null;
    this.libros = [];
    this.getLibros();
    if (this.log) {
      console.log(libro);
    }
  }

  obtenerLibros(): void {

    this.libroService.getLibros().subscribe(librosP => {
      const librosList = librosP
      librosList.forEach(element => {
        this.libros = [...this.libros, element];
      });
      this.loading = false;
    });
  }

}
