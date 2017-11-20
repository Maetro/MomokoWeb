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
      console.log(genero);
      console.log('selectLibro');
      this.generoDetailComponent.idCategoriaSeleccionada = genero.generoId;
      this.selectedGenero = genero;
    }

    nuevoGenero(): void {
      this.selectedGenero = null;
      const genero = new Genero;
      this.generoDetailComponent.idCategoriaSeleccionada = null;
      this.selectedGenero = genero;
    }

    volver(): void {
      this.selectedGenero = null;
    }

    actualizarOAnadirGenero(genero: Genero): void {
      this.selectedGenero = null;
      console.log('actualizarOAnadirGenero');
      this.generoDetailComponent.idCategoriaSeleccionada = genero.generoId;
      this.generos = [];
      this.getGeneros();
      console.log(genero);
    }

    onRowSelect(event) {
      console.log('Row Select');
      this.generoDetailComponent.idCategoriaSeleccionada = event.data.categoria.categoriaId;
  }

  }
