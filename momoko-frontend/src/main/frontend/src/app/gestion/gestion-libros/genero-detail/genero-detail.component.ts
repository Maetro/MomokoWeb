import { Categoria } from './../../../dtos/categoria';
import { LibroService } from './../../../services/libro.service';
import { Genero } from './../../../dtos/genero';
import { Component, OnInit, Input, Output, EventEmitter, SimpleChange } from '@angular/core';
import { Message } from 'primeng/primeng';
import { GeneralDataService } from 'app/services/general-data.service';
import { FileUploadService } from 'app/services/fileUpload.service';
import { GrowlModule, SelectItem } from 'primeng/primeng';

import { UtilService } from 'app/services/util.service';

@Component({
  selector: 'app-genero-detail',
  templateUrl: './genero-detail.component.html',
  styleUrls: ['./genero-detail.component.css']
})
export class GeneroDetailComponent implements OnInit {

    @Input() genero: Genero;

    @Output() onGeneroGuardado: EventEmitter<Genero> = new EventEmitter<Genero>();

    changeLog: string[] = [];

    esGeneroNuevo = false;

    msgs: Message[] = [];

    categorias: Categoria[];

    idCategoriaSeleccionada: number;

    customURL = false;

    constructor(private libroService: LibroService, private fileUploadService: FileUploadService,
      private generalDataService: GeneralDataService, private util: UtilService) {
    }

    ngOnInit(): void {
      this.generalDataService.getInformacionGeneral().subscribe(datos => {
        console.log('Init info general');
        this.categorias = datos.categorias;
      },
        error =>  {
          console.log('Error al recuperar los datos generales ', error);
        });
    }


    guardarGenero(): void {
      this.categorias.forEach(categoria => {
        const idCategoria = +this.idCategoriaSeleccionada;
        if (categoria.categoriaId === idCategoria) {
          this.genero.categoria = categoria;
        }
      });
      this.libroService.guardarGenero(this.genero)
        .subscribe(res => {
          if (res.estadoGuardado === 'CORRECTO') {
            this.showSuccess('Género guardado correctamente');
            this.onGeneroGuardado.emit(this.genero);
          } else {
            this.showError(res.listaErroresValidacion);
          }
        });
    }

    showSuccess(mensaje: string) {
      this.msgs = [];
      console.log(mensaje);
      this.msgs.push({ severity: 'success', summary: 'OK', detail: mensaje });
    }

    fileChangeCabecera($event): void {
      this.fileUploadService.fileChange($event, 'cabeceras-generos').subscribe
        (urlImagenNueva => {
          // Emit list event
          console.log(urlImagenNueva);
          this.showSuccess('Imagen guardada correctamente');
          this.genero.imagenCabeceraGenero = urlImagenNueva;
        },
        err => {
          // Log errors if any
          console.log(err);
        });
    }

    fileChangeIcono($event): void {
      this.fileUploadService.fileChange($event, 'iconos-generos').subscribe
        (urlImagenNueva => {
          // Emit list event
          console.log(urlImagenNueva);
          this.showSuccess('Imagen guardada correctamente');
          this.genero.iconoGenero = urlImagenNueva;
        },
        err => {
          // Log errors if any
          console.log(err);
        });
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

    urlChange(newValue: string) {
      this.customURL = true;
    }

    cambioNombre(newValue: string) {
      if (!this.customURL) {
        this.genero.urlGenero = encodeURIComponent(this.util.convertToSlug(newValue));
      }
    }

}
