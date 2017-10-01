
import { Component, OnInit } from '@angular/core';
import { LibroService } from 'app/services/libro.service';
import { FileUploadService } from 'app/services/fileUpload.service';
import { Genero } from 'app/dtos/genero';


@Component({
  selector: 'app-lista-generos',
  templateUrl: './lista-generos.component.html',
  styleUrls: ['./lista-generos.component.css']
})
export class ListaGenerosComponent implements OnInit {

    loading: boolean;

    title = 'Libros';
    generos: Genero[];
    selectedGenero: Genero;

    constructor(private libroService: LibroService, private fileUploadService: FileUploadService) {
      this.generos = [];

    }

    getGeneros(): void {
      console.log('service -> getGeneros()')
      this.libroService.getGeneros().then(generos => {
        generos.forEach(genero => {
          this.generos =  [ ...this.generos, genero ];
        });

      });
    }

    ngOnInit(): void {
      console.log('ngOnInit Lista generos')
      this.loading = true;
      this.libroService.getGeneros().then(generosP => {
        const generosList = generosP
        generosList.forEach(element => {
          this.generos =  [ ...this.generos, element ];
        });
        this.loading = false;
      });
    }

    selectLibro(genero: Genero) {
      console.log(genero);
      this.selectedGenero = genero;
    }

    nuevoGenero(): void {
      this.selectedGenero = null;
      const genero = new Genero;
      this.selectedGenero = genero;
    }

    volver(): void {
      this.selectedGenero = null;
    }

    actualizarOAnadirGenero(genero: Genero): void {
      this.selectedGenero = null;
      this.generos = [];
      this.getGeneros();
      console.log(genero);
    }

  }
