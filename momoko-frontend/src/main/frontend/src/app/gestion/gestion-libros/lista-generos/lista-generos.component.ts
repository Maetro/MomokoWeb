import { GeneroDetailComponent } from './../genero-detail/genero-detail.component';

import { Component, OnInit, ViewChild } from '@angular/core';
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

    @ViewChild(GeneroDetailComponent) generoDetailComponent: GeneroDetailComponent;

    constructor(private libroService: LibroService, private fileUploadService: FileUploadService) {
      console.log('Builder ListaGenerosComponent');
      this.generos = [];

    }

    getGeneros(): void {
      console.log('service -> getGeneros()')
      this.libroService.getGeneros().subscribe(generos => {
        generos.forEach(genero => {
          this.generos =  [ ...this.generos, genero ];
        });

      });
    }

    ngOnInit(): void {
      console.log('ngOnInit Lista generos')
      this.loading = true;
      this.libroService.getGeneros().subscribe(generosP => {
        const generosList = generosP
        generosList.forEach(element => {
          this.generos =  [ ...this.generos, element ];
        });
        this.loading = false;
      });
    }

    selectLibro(genero: Genero) {
      console.log('selectLibro');
      this.generoDetailComponent.idCategoriaSeleccionada = genero.generoId;
      this.selectedGenero = genero;
    }

    nuevoGenero(): void {
      console.log('nuevoGenero');
      this.selectedGenero = null;
      const genero = new Genero;
      this.generoDetailComponent.idCategoriaSeleccionada = null;
      this.selectedGenero = genero;
    }

    volver(): void {
      console.log('volver');
      this.selectedGenero = null;
    }

    actualizarOAnadirGenero(genero: Genero): void {
      console.log('actualizarOAnadirGenero ' + genero);
      this.selectedGenero = null;
      let encontrado = false;
      this.generos.forEach(gen => {
        if (gen.urlGenero === genero.urlGenero) {
          gen = genero;
          encontrado = true;
        }
      });
      if (!encontrado) {
        this.generos = [ ...this.generos, genero ];
      }
      this.generoDetailComponent.idCategoriaSeleccionada = null;
    }

    onRowSelect(event) {
      console.log('onRowSelect');
      this.generoDetailComponent.idCategoriaSeleccionada = event.data.categoria.categoriaId;
  }

  }
