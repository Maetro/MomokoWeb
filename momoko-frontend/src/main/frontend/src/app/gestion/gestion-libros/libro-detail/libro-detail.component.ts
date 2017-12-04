import { Component, Input, Output, OnInit, EventEmitter, OnChanges, SimpleChange, style } from '@angular/core';
import { Libro } from './../../../dtos/libro';
import { Genero } from './../../../dtos/genero';
import { LibroService } from './../../../services/libro.service';
import { FileUploadService } from './../../../services/fileUpload.service';
import { GeneralDataService } from './../../../services/general-data.service';

import { MultiSelectModule } from 'primeng/primeng';
import { Message } from 'primeng/components/common/api';
import { GrowlModule } from 'primeng/primeng';
import { SelectItem } from 'primeng/primeng';
import { CompleterService, CompleterData } from 'ng2-completer';
import { UtilService } from 'app/services/util.service';
import { Autor } from 'app/dtos/autor';

@Component({
  selector: 'app-libro-detail',
  templateUrl: './libro-detail.component.html',
  styles: [
    `.sidebox img {
      width: 100%;
      height: auto;
    }`
  ]
})
export class LibroDetailComponent implements OnInit, OnChanges {

  @Input() libro: Libro;

  @Output() onLibroGuardado: EventEmitter<Libro> = new EventEmitter<Libro>();

  listaGeneros: Genero[];

  generos: SelectItem[];

  selectedGeneros: string[] = [];

  changeLog: string[] = [];

  esLibroNuevo = false;

  msgs: Message[] = [];

  nombresEditoriales: string[];

  nombresAutores: string[];

  numeroAutores = 1;

  customURL = false;

  constructor(private libroService: LibroService, private generalDataService: GeneralDataService,
    private fileUploadService: FileUploadService, private util: UtilService) {
    this.generos = [];
  }

  ngOnInit(): void {
    this.getGeneros();
    this.generalDataService.getInformacionGeneral().subscribe(datos => {
      console.log('Init info general');
      this.nombresAutores = datos.nombresAutores;
      this.nombresEditoriales = datos.nombresEditoriales;
    },
      error =>  {
        console.log('Error al recuperar los datos generales ', error);
      });
  }

  ngOnChanges(changes: { [propKey: string]: SimpleChange }) {
    const log: string[] = [];
    for (const propName of Object.keys(changes)) {
      const changedProp = changes[propName];
      const to = JSON.stringify(changedProp.currentValue);
      if (changedProp.isFirstChange()) {
        log.push(`Initial value of ${propName} set to ${to}`);
      } else {
        const from = JSON.stringify(changedProp.previousValue);
        log.push(`${propName} changed from ${from} to ${to}`);
        if (changedProp.currentValue != null) {
          const l: Libro = changedProp.currentValue;
          this.selectedGeneros = [];
          if (l.libroId != null) {
            this.esLibroNuevo = false;
          } else {
            this.esLibroNuevo = true;
          }
          const generosLibro: Genero[] = l.generos;
          if (generosLibro != null) {
            generosLibro.forEach(generoLibro => {
              this.generos.forEach(genero => {
                if (generoLibro.generoId === genero.value) {
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
    this.libroService.getGeneros().subscribe(generos => {
      this.listaGeneros = generos;
      generos.forEach(genero => {
        this.generos.push({ label: ' ' + genero.nombre, value: genero.generoId });

      });

    });
  }

  onChange(event: any) {
    console.log(event);
    this.libro.generos = [];
    event.value.forEach(generoSeleccionadoId => {
      this.listaGeneros.forEach(genero => {
        if (genero.generoId === generoSeleccionadoId) {
          this.libro.generos.push(genero);
        }
      });
    });
  }

  guardarLibro(): void {
    this.libroService.guardarLibro(this.libro)
      .subscribe(res => {
        if (res.estadoGuardado === 'CORRECTO') {
          this.showSuccess('Libro guardado correctamente');
          this.onLibroGuardado.emit(this.libro);
        } else {
          this.showError(res.listaErroresValidacion);
        }
      })
  }

  showSuccess(mensaje: string) {
    this.msgs = [];
    console.log(mensaje);
    this.msgs.push({ severity: 'success', summary: 'OK', detail: mensaje });
  }

  showError(mensaje: string[]) {
    this.msgs = [];
    console.log(mensaje);
    let mensajeTotal = '';
    mensaje.forEach(element => {
      mensajeTotal += element + '<br/>';
    });
    console.log(mensajeTotal);
    this.msgs.push({ severity: 'error', summary: 'ERROR', detail: mensajeTotal });
  }

  fileChange($event): void {
    this.fileUploadService.fileChange($event, 'portadas').subscribe
      (urlImagenNueva => {
        // Emit list event
        console.log(urlImagenNueva);
        this.showSuccess('Imagen guardada correctamente');
        this.libro.urlImagen = urlImagenNueva;
      },
      err => {
        // Log errors if any
        console.log(err);
      });
  }

  urlChange(newValue: string) {
    this.customURL = true;
  }

  cambioTitulo(newValue: string) {
    if (!this.customURL) {
      this.libro.urlLibro = encodeURIComponent(this.util.convertToSlug(newValue));
    }
  }

  crearAutor() {
    const autor = new Autor;
    this.libro.autores.push(autor);
  }

}