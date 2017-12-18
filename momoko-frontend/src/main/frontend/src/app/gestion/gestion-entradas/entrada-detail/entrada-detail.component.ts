import { GaleriaService } from './../../../services/galeria.service';
import { UtilService } from './../../../services/util.service';

import { Etiqueta } from 'app/dtos/etiqueta';
import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { Entrada } from 'app/dtos/entrada';
import { EntradaService } from 'app/services/entrada.service';

import { Message, Dropdown } from 'primeng/primeng';
import { GeneralDataService } from 'app/services/general-data.service';
import { SelectItem } from 'primeng/components/common/selectitem';

import { FileUploadService } from 'app/services/fileUpload.service';
import { Libro } from 'app/dtos/libro';
import { Galeria } from 'app/dtos/galeria';
import { AfterViewInit } from '@angular/core/src/metadata/lifecycle_hooks';

declare var $: any;

@Component({
  selector: 'app-entrada-detail',
  templateUrl: './entrada-detail.component.html',
  styleUrls: ['./entrada-detail.component.css']
})

export class EntradaDetailComponent implements OnInit, AfterViewInit {

  @Input() entrada: Entrada;

  @Output() onEntradaGuardada: EventEmitter<Entrada> = new EventEmitter<Entrada>();


  msgs: Message[] = [];
  customURL = false;

  nombresEditoriales: string[];

  titulosLibros: SelectItem[];
  tiposEntrada: SelectItem[];
  estadosEntrada: SelectItem[];
  nombresGalerias: SelectItem[];
  nicksEditores: SelectItem[];

  galerias: Galeria[];

  etiquetas: string[];

  fraseLibrosEscoger = 'Escoge libros';
  fraseGalerias= 'Escoge galería';
  fraseEditorEscoger = 'Escoge autor de la entrada';
  selectedGaleria: string;

  constructor(private entradaService: EntradaService, private generalDataService: GeneralDataService,
    private fileUploadService: FileUploadService, private util: UtilService, private galeriaService: GaleriaService) {
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
    this.nombresGalerias = [];
    this.nicksEditores = [];
    this.generalDataService.getInformacionGeneral().subscribe(datos => {
      console.log('Init info general');
      const libros = datos.titulosLibros;
      libros.forEach((libro: string) => {
        this.titulosLibros.push({ label: ' ' + libro, value: libro });
      });
      const editores = datos.nicksEditores;
      editores.forEach((editor: string) => {
        this.nicksEditores.push({ label: ' ' + editor, value: editor });
      });
    },
      error => {
        console.log('Error al recuperar los datos generales ', error);
      });
  }

  ngAfterViewInit(): void {
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
    const contain = <HTMLElement> document.getElementById('editor').firstChild;
    this.entrada.contenidoEntrada = contain.innerHTML;
    console.log(this.entrada.contenidoEntrada);
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

  buscarGalerias(): void {

    this.galeriaService.getGalerias().subscribe(galerias => {
      this.galerias = galerias;
      galerias.forEach(galeria => {
        this.nombresGalerias.push({ label: ' ' + galeria.nombreGaleria, value: galeria.urlGaleria });
      });
    })
  }

  esTipoVideo(): boolean {
    return this.entrada.tipoEntrada === 4;
  }

  insertarGaleria($event) {
    console.log('Insertando galeria');
    this.galerias.forEach(galeria => {
      if (galeria.urlGaleria === $event.value[0]) {
        console.log(galeria);
        const tag = '[momoko-galeria-' + galeria.urlGaleria + ']';
        const editor = $('#editor').data('quill');

        const range = editor.getSelection();
        let puntoAInsertar: number;
        if (range) {
          if (range.length === 0) {
            console.log('User cursor is at index', range.index);
            puntoAInsertar = range.index;
          } else {
            const text = editor.getText(range.index, range.length);
            console.log('User has highlighted: ', text);
            puntoAInsertar = range.index;
          }
        } else {
          console.log('User cursor is not in editor');
          puntoAInsertar = editor.getLength();
        }
        editor.insertText(puntoAInsertar, '\n' + tag);
      }
    });
  }

}
