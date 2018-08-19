import { Component, OnInit } from '@angular/core';

import { DataTableModule, SharedModule } from 'primeng/primeng';
import { environment } from 'environments/environment';
import { Libro } from '../../../../dtos/libro';
import { Genero } from '../../../../dtos/genre/genero';
import { LibroService } from '../../../../services/libro.service';
import { FileUploadService } from '../../services/file-upload.service';
import { Autor } from '../../../../dtos/autor';
import { Editorial } from '../../../../dtos/editorial';

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

  constructor(
    private libroService: LibroService,
    private fileUploadService: FileUploadService
  ) {
    this.libros = [];
  }

  getLibros(): void {
    this.libroService.getLibros().then(libros => {
      libros.forEach(element => {
        this.libros = [...this.libros, element];
      });
    });
  }

  ngOnInit(): void {
    this.loading = true;
    this.libroService.getLibros().then(librosP => {
      const librosList = librosP;
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
    const libro = new Libro();
    libro.autores = [];
    const autor = new Autor();
    libro.autores.push(autor);
    libro.generos = [];
    libro.editorial = new Editorial();
    this.selectedLibro = libro;
  }

  volver(): void {
    this.selectedLibro = null;
  }

  actualizarOAnadirLibro(libro: Libro): void {
    if (this.log) {
      console.log(libro);
    }
    this.selectedLibro = null;
    this.libros = [];
    this.getLibros();
    if (this.log) {
      console.log(libro);
    }
  }

  obtenerLibros(): void {
    this.libroService.getLibros().then(librosP => {
      const librosList = librosP;
      librosList.forEach(element => {
        this.libros = [...this.libros, element];
      });
      this.loading = false;
    });
  }
}