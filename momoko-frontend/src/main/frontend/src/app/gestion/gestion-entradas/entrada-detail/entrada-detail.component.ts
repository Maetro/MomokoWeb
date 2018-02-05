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
import { environment } from 'environments/environment';

declare var Quill: any;
declare var $: any;

import { ImageResize } from 'quill-image-resize-module';
import { Fila } from 'app/gestion/gestion-entradas/fila';
import { Columna } from 'app/gestion/gestion-entradas/columna';
import { Event } from '@angular/router/src/events';
const Parchment = Quill.import('parchment');
Quill.register('imageResize', ImageResize);

Quill.register(new Parchment.Attributor.Style('display', 'display', {
  whitelist: ['inline']
}));
Quill.register(new Parchment.Attributor.Style('float', 'float', {
  whitelist: ['left', 'right', 'center']
}));
Quill.register(new Parchment.Attributor.Style('margin', 'margin', {}));

@Component({
  selector: 'app-entrada-detail',
  templateUrl: './entrada-detail.component.html',
  styleUrls: ['./entrada-detail.component.css']
})

export class EntradaDetailComponent implements OnInit, AfterViewInit {
  bootstrapcolumn: string;
  numeroColumna: number;
  numeroFila: number;
  contenidoEntrada: string;

  idEditor: string;

  private log = environment.log;

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
  numeroColumnas = 1;

  galerias: Galeria[];

  etiquetas: string[];

  date = new Date();

  fraseLibrosEscoger = 'Escoge libros';
  fraseGalerias = 'Escoge galería';
  fraseEditorEscoger = 'Escoge autor de la entrada';
  selectedGaleria: string;
  es: any;

  filas: Fila[];


  urlImageServer = environment.urlFiles;

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
      if (this.log) {
        console.log('Init info general');
      }
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
        if (this.log) {
          console.log('Error al recuperar los datos generales ', error);
        }
      });
    this.es = {
      firstDayOfWeek: 1,
      dayNames: ['domingo', 'lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado'],
      dayNamesShort: ['dom', 'lun', 'mar', 'mié', 'jue', 'vie', 'sáb'],
      dayNamesMin: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
      monthNames: ['enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio', 'julio', 'agosto', 'septiembre', 'octubre',
        'noviembre', 'diciembre'],
      monthNamesShort: ['ene', 'feb', 'mar', 'abr', 'may', 'jun', 'jul', 'ago', 'sep', 'oct', 'nov', 'dic'],
      today: 'Hoy',
      clear: 'Borrar'
    }


    const today = new Date();
    const month = today.getMonth();
    const year = today.getFullYear();
    const prevMonth = (month === 0) ? 11 : month - 1;
    const prevYear = (prevMonth === 11) ? year - 1 : year;
    const nextMonth = (month === 11) ? 0 : month + 1;
    const nextYear = (nextMonth === 0) ? year + 1 : year;

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
    if (this.log) {
      console.log(mensaje);
    }
    this.msgs.push({ severity: 'success', summary: 'OK', detail: mensaje });
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

  fileChange($event): void {
    this.fileUploadService.fileChange($event, 'imagenes-destacadas').subscribe
      (urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        const partesURL = urlImagenNueva.split('/');
        const partes = partesURL[partesURL.length - 1].split('.');
        const urlImagen = this.urlImageServer + 'imagenes-destacadas/' + this.util.convertToSlug(partes[0]) + '.' + partes[1];

        this.showSuccess('Imagen guardada correctamente');
        this.entrada.imagenDestacada = urlImagen;
      },
      err => {
        // Log errors if any
        if (this.log) {
          console.log(err);
        }
      });
  }

  guardarEntrada(): void {
    this.entrada.etiquetas = [];
    let texto = '';
    if (this.filas != null && this.filas.length > 0) {
      this.filas.forEach(fila => {
        texto += '<div class="row">';
        if (fila.columnas != null && fila.columnas.length > 0) {

          fila.columnas.forEach(columna => {
            texto += '<div class="' + fila.bootstrapcolumn + '">';
            const textoEditor = <HTMLElement>document.getElementById('editor-' + fila.numFila + '-' + columna.numcolumna).firstChild;
            texto += textoEditor.innerHTML;
            texto += '</div>';
          });

        }
        texto += '</div>';
      });
    }
    console.log('Restructurado');
    this.entrada.contenidoEntrada = texto;
    if (this.log) {
      console.log(this.entrada.contenidoEntrada);
    }
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
    this.entrada.fechaAlta = this.date;
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
    if (this.log) {
      console.log('Insertando galeria');
    }
    this.galerias.forEach(galeria => {
      if (galeria.urlGaleria === $event.value[0]) {
        if (this.log) {
          console.log(galeria);
        }
        const tag = '[momoko-galeria-' + galeria.urlGaleria + ']';
        const editor = $('#editor').data('quill');

        const range = editor.getSelection();
        let puntoAInsertar: number;
        if (range) {
          if (range.length === 0) {
            if (this.log) {
              console.log('User cursor is at index', range.index);
            }
            puntoAInsertar = range.index;
          } else {
            const text = editor.getText(range.index, range.length);
            if (this.log) {
              console.log('User has highlighted: ', text);
            }
            puntoAInsertar = range.index;
          }
        } else {
          if (this.log) {
            console.log('User cursor is not in editor');
          }
          puntoAInsertar = editor.getLength();
        }
        editor.insertText(puntoAInsertar, '\n' + tag);
      }
    });
  }

  anadirFila() {
    console.log('Anadir Fila');
    const numFila = this.filas.length;
    const nuevaFila = new Fila(numFila, '');
    nuevaFila.bootstrapcolumn = 'col-sm-12';
    this.filas.push(nuevaFila);
    this.crearEditorAsync('editor-' + numFila + '-' + 0, '', nuevaFila.numFila, 0);
  }

  crearEditorAsync(id: string, contenidoEntrada: string, numeroFila: number, numeroColumna: number): void {
    this.idEditor = id;
    this.contenidoEntrada = contenidoEntrada;
    this.numeroFila = numeroFila;
    this.numeroColumna = numeroColumna;
    console.log('async');
    // tslint:disable-next-line:no-shadowed-variable
    const that = this;
    setTimeout(function () {
      return that.crearEditor();
    }, 100);
  }

  crearEditor(): void {

    if (this.log) {
      console.log('Quill');
    }

    const container = document.getElementById(this.idEditor);
    const toolbarOptions = [
      ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
      ['blockquote', 'code-block'],

      [{ 'header': 1 }, { 'header': 2 }],               // custom button values
      [{ 'list': 'ordered' }, { 'list': 'bullet' }],
      [{ 'script': 'sub' }, { 'script': 'super' }],      // superscript/subscript
      [{ 'indent': '-1' }, { 'indent': '+1' }],          // outdent/indent
      [{ 'direction': 'rtl' }],                         // text direction

      [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
      [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

      [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
      [{ 'font': [] }],
      [{ 'align': [] }],
      ['link', 'image', 'video', 'formula']
      ['clean'],
      ['omega']                                         // remove formatting button
    ];

    const editor = new Quill(container, {
      theme: 'snow',
      modules: {
        toolbar: '#toolbar-container-' + this.numeroFila + '-' + this.numeroColumna,
        imageResize: {}
      }
    });

    const toolbar = editor.getModule('toolbar');
    toolbar.addHandler('omega', function () {
      console.log('omega')
    });


    const customButton = document.querySelector('.ql-omega');
    customButton.addEventListener('click', function () {
      console.log('Add libro');
      const range = editor.getSelection();
      if (range) {
        // tslint:disable-next-line:max-line-length
        editor.insertText(range.index, '[momoko-libro img="imagen" titulo="" autor="" texto="Texto que acompaña al libro" colorFondo="Negro" posicionLibro="left"]');
      }
    });

    editor.pasteHTML(this.contenidoEntrada);
    editor.on('text-change', function (delta, oldDelta, source) {
      if (this.log) {
        console.log(editor.getContents());
      }
    });
    $(container).data('quill', editor);
  }

  pintarColumnas(event: Event, fila: Fila) {
    if (this.log) {
      console.log('Pintar columnas');
    }
    this.modificarFilaPorNumeroColumnas(fila);

    const numColumna = fila.numeroColumnas - 1;

    const columna = new Columna(numColumna, '');
    fila.columnas.push(columna);
    console.log('Anadir Columna');
    this.crearEditorAsync('editor-' + fila.numFila + '-' + numColumna, '', fila.numFila, numColumna);
  }

  modificarFilaPorNumeroColumnas(fila: Fila): void {
    switch (fila.numeroColumnas) {
      case 1:
        fila.bootstrapcolumn = 'col-sm-12';
        break;
      case 2:
        fila.bootstrapcolumn = 'col-sm-6';
        break;
      case 3:
        fila.bootstrapcolumn = 'col-sm-4';
        break;
      case 4:
        fila.bootstrapcolumn = 'col-sm-3';
        break;
      case 5:
        fila.bootstrapcolumn = 'col-sm-2';
        break;
      case 6:
        fila.bootstrapcolumn = 'col-sm-2';
        break;
      default:
        fila.bootstrapcolumn = 'col-sm-12';
    }
  }

  crearEditoresAsync(): void {
    if (this.filas !== null && this.filas.length > 0) {
      this.filas.forEach(fila => {
        if (fila.columnas !== null && fila.columnas.length > 0) {
          fila.columnas.forEach(columna => {
            this.crearEditorConTexto('editor-' + fila.numFila + '-' + columna.numcolumna,  columna.texto, fila.numFila, columna.numcolumna);
          });
        }
      });

    }
  }

  crearEditorConTexto(idEditor: string, texto: string, numFila: number, numColumna: number): void {

    if (this.log) {
      console.log('Quill');
    }

    const container = document.getElementById(idEditor);
    const toolbarOptions = [
      ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
      ['blockquote', 'code-block'],

      [{ 'header': 1 }, { 'header': 2 }],               // custom button values
      [{ 'list': 'ordered' }, { 'list': 'bullet' }],
      [{ 'script': 'sub' }, { 'script': 'super' }],      // superscript/subscript
      [{ 'indent': '-1' }, { 'indent': '+1' }],          // outdent/indent
      [{ 'direction': 'rtl' }],                         // text direction

      [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
      [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

      [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
      [{ 'font': [] }],
      [{ 'align': [] }],
      ['link', 'image', 'video', 'formula']
      ['clean'],
      ['omega']                                         // remove formatting button
    ];

    const editor = new Quill(container, {
      theme: 'snow',
      modules: {
        toolbar: '#toolbar-container-' + numFila + '-' + numColumna,
        imageResize: {}
      }
    });

    const toolbar = editor.getModule('toolbar');
    toolbar.addHandler('omega', function () {
      console.log('omega')
    });


    const customButton = document.querySelector('.ql-omega');
    customButton.addEventListener('click', function () {
      console.log('Add libro');
      const range = editor.getSelection();
      if (range) {
        // tslint:disable-next-line:max-line-length
        editor.insertText(range.index, '[momoko-libro img="imagen" titulo="" autor="" texto="Texto que acompaña al libro" colorFondo="Negro" posicionLibro="left"]');
      }
    });
    editor.pasteHTML(texto);
    $(container).data('quill', editor);
  }

}
