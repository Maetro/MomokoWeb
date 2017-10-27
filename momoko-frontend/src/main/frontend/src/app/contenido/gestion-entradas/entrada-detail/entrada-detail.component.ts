
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
import { GaleriaFormImplComponent } from 'app/contenido/gestion-entradas/anadir-galeria/galeria-form-impl.component';


@Component({
  selector: 'app-entrada-detail',
  templateUrl: './entrada-detail.component.html'
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



  constructor(private entradaService: EntradaService, private generalDataService: GeneralDataService,
    private fileUploadService: FileUploadService) {
    this.tiposEntrada = [];
    this.tiposEntrada.push({ label: 'Noticia', value: 1 });
    this.tiposEntrada.push({ label: 'Análisis', value: 2 });
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
      this.titulosLibros.push({ label: ' ' + 'No asociar', value: null });
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
      this.entrada.urlEntrada = encodeURIComponent(this.convertToSlug(newValue));
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

  convertToSlug(text: string) {

    const from = 'ãàáäâẽèéëêìíïîõòóöôùúüûñç·/_,:;';
    const to = 'aaaaaeeeeeiiiiooooouuuunc------';
    for (let i = 0, l = from.length; i < l; i++) {
      text = text.replace(new RegExp(from.charAt(i), 'g'), to.charAt(i));
    }

    return text
      .toLowerCase()
      .replace(/[^\w ]+/g, '')
      .replace(/ +/g, '-')
      ;
  }

  showSuccess(mensaje: string) {
    this.msgs = [];
    console.log(mensaje);
    this.msgs.push({ severity: 'success', summary: 'OK', detail: mensaje });
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
    this.etiquetas.forEach(etiqueta => {
     const et = new Etiqueta();
     et.nombreEtiqueta = etiqueta;
     this.entrada.etiquetas.push(et);
    });
    if (this.entrada.tipoEntrada == null) {
      this.entrada.tipoEntrada = 1;
    }
    if (this.entrada.estadoEntrada == null) {
      this.entrada.estadoEntrada = 1;
    }
    this.entradaService.guardarEntrada(this.entrada)
      .then(res => {
        this.showSuccess('Entrada guardada correctamente');
        // this.onEntradaGuardada.emit(this.entrada);
      })
      .catch();
  }

  anadirGaleria(): void {
    const galerias = new GaleriaItem(GaleriaFormImplComponent, {headline: 'Openings in all departments',
      body: 'Apply today'});
    this.anadirGaleriaComponent.loadComponent(galerias);
  }

}
