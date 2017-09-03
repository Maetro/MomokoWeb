import { Component, Input, OnInit, OnChanges, SimpleChange, style } from '@angular/core';
import { Libro } from './../../dtos/libro';
import { Genero } from './../../dtos/genero';
import { LibroService } from './../../libro.service';
import { FileUploadService } from './../../services/fileUpload.service';

import { MultiSelectModule } from 'primeng/primeng';

import { SelectItem } from 'primeng/primeng';

@Component({
  selector: 'libro-detail',
  templateUrl: './libro-detail.component.html',
  styles: [`.sidebox img {
    width: 100%;
    height: auto;
  }`]
})
export class LibroDetailComponent implements OnInit, OnChanges {

  @Input() libro: Libro;

  listaGeneros: Genero[];

  generos: SelectItem[];

  selectedGeneros: string[] = [];

  changeLog: string[] = [];

  esLibroNuevo:boolean = false;

  constructor(private libroService: LibroService, private fileUploadService: FileUploadService) {
    this.generos = [];
  }

  ngOnInit(): void {
    console.log("ngOnInit Lista Generos")
    this.getGeneros();
  }

  ngOnChanges(changes: { [propKey: string]: SimpleChange }) {
    let log: string[] = [];
    for (let propName in changes) {
      let changedProp = changes[propName];
      let to = JSON.stringify(changedProp.currentValue);
      if (changedProp.isFirstChange()) {
        log.push(`Initial value of ${propName} set to ${to}`);
      } else {
        let from = JSON.stringify(changedProp.previousValue);
        log.push(`${propName} changed from ${from} to ${to}`);
        if (changedProp.currentValue != null) {
          let l: Libro = changedProp.currentValue;
          this.selectedGeneros = [];
          if (l.libroId != null){
            this.esLibroNuevo = false;
          } else{
            this.esLibroNuevo = true;
          }
          let generosLibro: Genero[] = l.generos;
          if (generosLibro != null) {
            generosLibro.forEach(generoLibro => {
              this.generos.forEach(genero => {
                if (generoLibro.generoId == genero.value) {
                  this.selectedGeneros.push(genero.value);
                }
              });
            });
          }
        }
      }
    }
    this.changeLog.push(log.join(', '));
  }

  getGeneros(): void {
    console.log("service -> getGeneros()")
    this.libroService.getGeneros().then(generos => {
      this.listaGeneros = generos;
      generos.forEach(genero => {
        this.generos.push({ label: genero.nombre, value: genero.generoId });

      });

    });
  }



  onChange(event: any) {
    console.log(event);
    this.libro.generos = [];
    event.value.forEach(generoSeleccionadoId => {
      this.listaGeneros.forEach(genero => {
        if (genero.generoId == generoSeleccionadoId) {
          this.libro.generos.push(genero);
        }
      });
    });
  }

  crearLibro() : void{
    this.libroService.crearLibro(this.libro);
  }


  fileChange($event) : void{
    this.fileUploadService.fileChange($event).subscribe
      (urlImagenNueva => {
        // Emit list event
        console.log(urlImagenNueva);
        this.libro.urlImagen = urlImagenNueva;
       },
      err => {
      // Log errors if any
      console.log(err);
    });
  }

}
