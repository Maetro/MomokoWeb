
import { Component, OnInit, Input, Output, EventEmitter, SimpleChange } from '@angular/core';
import { Message } from 'primeng/primeng';

import { environment } from 'environments/environment';
import { Genero } from '../../../../dtos/genre/genero';
import { Categoria } from '../../../../dtos/categoria';
import { LibroService } from '../../../../services/libro.service';
import { FileUploadService } from '../../services/file-upload.service';
import { GeneralDataService } from '../../services/general-data.service';
import { UtilService } from '../../../../services/util/util.service';

@Component({
  selector: 'app-genero-detail',
  templateUrl: './genero-detail.component.html',
  styleUrls: ['./genero-detail.component.css']
})
export class GeneroDetailComponent implements OnInit {

  private log = environment.log;

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
      if (this.log) {
        console.log('Init info general');
      }
      this.categorias = datos.categorias;
    },
      error => {
        if (this.log) {
          console.log('Error al recuperar los datos generales ', error);
        }
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
    if (this.log) {
      console.log(mensaje);
    }
    this.msgs.push({ severity: 'success', summary: 'OK', detail: mensaje });
  }

  fileChangeCabecera($event): void {
    this.fileUploadService.fileChange($event, 'cabeceras-generos').subscribe
      (urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        this.showSuccess('Imagen guardada correctamente');
        this.genero.imagenCabeceraGenero = urlImagenNueva;
      },
      err => {
        // Log errors if any
        if (this.log) {
          console.log(err);
        }
      });
  }

  fileChangeIcono($event): void {
    this.fileUploadService.fileChange($event, 'iconos-generos').subscribe
      (urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        this.showSuccess('Imagen guardada correctamente');
        this.genero.iconoGenero = urlImagenNueva;
      },
      err => {
        // Log errors if any
        if (this.log) {
          console.log(err);
        }
      });
  }

  showError(mensaje: string[]) {
    this.msgs = [];
    if (this.log) {
      console.log(mensaje);
    }
    let mensajeTotal = '';
    mensaje.forEach(element => {
      mensajeTotal += element + '<br/>';
    });
    if (this.log) {
      console.log(mensajeTotal);
    }
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
