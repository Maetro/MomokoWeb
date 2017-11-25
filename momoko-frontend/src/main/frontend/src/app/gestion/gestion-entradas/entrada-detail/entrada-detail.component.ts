import { UtilService } from './../../../services/util.service';

import { GaleriaItem } from './../anadir-galeria/galeria-item';
import { Etiqueta } from 'app/dtos/etiqueta';
import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { Entrada } from 'app/dtos/entrada';
import { EntradaService } from 'app/services/entrada.service';

import { Message } from 'primeng/primeng';
import { GeneralDataService } from 'app/services/general-data.service';
import { SelectItem } from 'primeng/components/common/selectitem';

import { AnadirGaleriaComponent } from '../anadir-galeria/anadir-galeria.component';
import { FileUploadService } from 'app/services/fileUpload.service';
import { GaleriaFormImplComponent } from 'app/gestion/gestion-entradas/anadir-galeria/galeria-form-impl.component';
import { Libro } from 'app/dtos/libro';


@Component({
  selector: 'app-entrada-detail',
  templateUrl: './entrada-detail.component.html',
  styleUrls: ['./entrada-detail.component.css']
})
export class EntradaDetailComponent implements OnInit {

  @Input() entrada: Entrada;

  @Output() onEntradaGuardada: EventEmitter<Entrada> = new EventEmitter<Entrada>();

  @ViewChild(AnadirGaleriaComponent) anadirGaleriaComponent: AnadirGaleriaComponent;

  msgs: Message[] = [];
  customURL = false;

  nombresEditoriales: string[];

  titulosLibros: SelectItem[];
  tiposEntrada: SelectItem[];
  estadosEntrada: SelectItem[];

  etiquetas: string[];

  fraseLibrosEscoger = 'Escoge libros';

  constructor(private entradaService: EntradaService, private generalDataService: GeneralDataService,
    private fileUploadService: FileUploadService, private util: UtilService) {
    this.tiposEntrada = [];
    this.tiposEntrada.push({ label: 'Noticia', value: 1 });
    this.tiposEntrada.push({ label: 'Análisis', value: 2 });
    this.tiposEntrada.push({ label: 'Misceláneos', value: 3 });
    this.tiposEntrada.push({ label: 'Vídeo', value: 4 })
    this.estadosEntrada = [];
    this.estadosEntrada.push({ label: 'Borrador', value: 1 });
    this.estadosEntrada.push({ label: 'Publicada', value: 2 });
    this.estadosEntrada.push({ label: 'Borrada', value: 3 });
  }

  ngOnInit() {
    this.titulosLibros = [];

    this.generalDataService.getInformacionGeneral().subscribe(datos => {
      console.log('Init info general');
      const libros = datos.titulosLibros;
      libros.forEach((libro: string) => {
        this.titulosLibros.push({ label: ' ' + libro, value: libro });
      });
    },
      error => {
        console.log('Error al recuperar los datos generales ', error);
      });
  }

  cambioTitulo(newValue: string) {
    if (!this.customURL) {
      this.entrada.urlEntrada = encodeURIComponent(this.util.convertToSlug(newValue));
    }
  }

  urlChange(newValue: string) {
    this.customURL = true;
  }

  actualizarContenido(contenido: string) {
    this.entrada.contenidoEntrada = contenido;
  }

  actualizarResumen(resumen: string) {
    this.entrada.resumenEntrada = resumen;
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
    this.fileUploadService.fileChange($event, 'imagenes-destacadas').subscribe
      (urlImagenNueva => {
        // Emit list event
        console.log(urlImagenNueva);
        this.showSuccess('Imagen guardada correctamente');
        this.entrada.imagenDestacada = urlImagenNueva;
      },
      err => {
        // Log errors if any
        console.log(err);
      });
  }

  guardarEntrada(): void {
    this.entrada.etiquetas = [];
    if (this.etiquetas != null) {
      this.etiquetas.forEach(etiqueta => {
        const et = new Etiqueta();
        et.nombreEtiqueta = etiqueta;
        this.entrada.etiquetas.push(et);
      });
    }
    if (this.entrada.tipoEntrada == null) {
      this.entrada.tipoEntrada = 1;
    }
    if (this.entrada.estadoEntrada == null) {
      this.entrada.estadoEntrada = 1;
    }

    this.entradaService.guardarEntrada(this.entrada)
      .subscribe(res => {
        if (res.estadoGuardado === 'CORRECTO') {
          this.showSuccess('Entrada guardada correctamente');
          this.onEntradaGuardada.emit(this.entrada);
        } else {
          this.showError(res.listaErroresValidacion);
        }
      });
  }

  anadirGaleria(): void {
    const galerias = new GaleriaItem(GaleriaFormImplComponent, {
      headline: 'Openings in all departments',
      body: 'Apply today'
    });
    this.anadirGaleriaComponent.loadComponent(galerias);
  }

  esTipoVideo(): boolean {
    return this.entrada.tipoEntrada === 4;
  }

}
