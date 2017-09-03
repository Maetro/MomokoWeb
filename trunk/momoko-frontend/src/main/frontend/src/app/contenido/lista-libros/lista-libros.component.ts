import { Editorial } from './../../dtos/editorial';
import { Component, OnInit } from '@angular/core';
import { Libro } from './../../dtos/libro';
import { Autor } from './../../dtos/autor';
import { LibroService } from './../../libro.service';
import { FileUploadService } from './../../services/fileUpload.service';

@Component({
  selector: 'app-lista-libros',
  templateUrl: './lista-libros.component.html',
  styleUrls: ['./lista-libros.component.css']
})
export class ListaLibrosComponent implements OnInit {

  title = 'Libros';
  libros: Libro[];
  selectedLibro: Libro;

  constructor(private libroService: LibroService, private fileUploadService: FileUploadService) { }

  getLibros(): void {
    console.log("service -> getLibros()")
    this.libroService.getLibros().then(libros => this.libros = libros);
  }

  ngOnInit(): void {
    console.log("ngOnInit Lista libros")
    this.getLibros();
  }

  onSelect(libro: Libro): void {
    this.selectedLibro = libro;
  }

  nuevoLibro(): void{
    this.selectedLibro = null;
    var libro = new Libro;
    libro.autores = [];
    var autor = new Autor;
    libro.autores.push(autor);
    libro.generos = [];
    libro.editorial = new Editorial;
    this.selectedLibro = libro;
  }

  volver(): void{
    this.selectedLibro = null;
  }

}
