import { Component, OnInit } from '@angular/core';
import { AfterViewInit } from '@angular/core/src/metadata/lifecycle_hooks';
import { ActivatedRoute, Router } from '@angular/router';
import { Event } from '@angular/router/src/events';
import { environment } from 'environments/environment';
import { SelectItem } from 'primeng/components/common/selectitem';
import { Message, MessageService } from 'primeng/primeng';
import { ImageResize } from 'quill-image-resize-module';
import { Entrada, EntryStatusEnum, EntryTypeEnum } from '../../../../dtos/entrada';
import { Etiqueta } from '../../../../dtos/etiqueta';
import { Galeria } from '../../../../dtos/galeria';
import { UtilService } from '../../../../services/util/util.service';
import { FileUploadService } from '../../services/file-upload.service';
import { GaleriaService } from '../../services/galeria.service';
import { GeneralDataService } from '../../services/general-data.service';
import { Columna } from '../columna';
import { SaveEntryRequest } from '../dtos/save-entry-request';
import { EntryService } from '../entry.service';
import { Fila } from '../fila';




declare var Quill: any;
declare var $: any;

const Parchment = Quill.import('parchment');
Quill.register('imageResize', ImageResize);

Quill.register(
  new Parchment.Attributor.Style('display', 'display', {
    whitelist: ['inline']
  })
);
Quill.register(
  new Parchment.Attributor.Style('float', 'float', {
    whitelist: ['left', 'right', 'center']
  })
);
Quill.register(new Parchment.Attributor.Style('margin', 'margin', {}));

@Component({
  selector: 'app-entry-form',
  templateUrl: './entry-form.component.html',
  styleUrls: ['./entry-form.component.scss']
})
export class EntryFormComponent implements OnInit, AfterViewInit {
  bootstrapcolumn: string;
  numeroColumna: number;
  numeroFila: number;
  contenidoEntrada: string;

  idEditor: string;

  private log = environment.log;

  entryUrl: string;

  entrada: Entrada;

  msgs: Message[] = [];
  customURL = false;
  customZona = false;

  nombresEditoriales: string[];

  titulosLibros: SelectItem[];
  nombresSagas: SelectItem[];
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
  fraseSagasEscoger = 'Escoge sagas';
  selectedGaleria: string;
  es: any;

  filas: Fila[];

  fondos = [{ id: 1, name: 'Blanco' }, { id: 2, name: 'Negro' }];

  urlImageServer = environment.urlFiles;

  bookTemplateRange: any;
  bookTemplateEditor: any;
  selectedBookTemplate: string;

  constructor(
    private entryService: EntryService,
    private generalDataService: GeneralDataService,
    private fileUploadService: FileUploadService,
    private util: UtilService,
    private galeriaService: GaleriaService,
    private route: ActivatedRoute,
    private router: Router,
    private messageService: MessageService
  ) {
    this.tiposEntrada = [];
    this.tiposEntrada.push({ label: 'Noticia', value: 'NEWS' });
    this.tiposEntrada.push({ label: 'Análisis', value: 'OPINION' });
    this.tiposEntrada.push({ label: 'Misceláneos', value: 'MISCELLANEOUS' });
    this.tiposEntrada.push({ label: 'Vídeo', value: 'VIDEO' });
    this.estadosEntrada = [];
    this.estadosEntrada.push({ label: 'Borrador', value: 1 });
    this.estadosEntrada.push({ label: 'Publicada', value: 2 });
    this.estadosEntrada.push({ label: 'Borrada', value: 3 });
  }

  ngOnInit() {
    this.titulosLibros = [];
    this.nombresGalerias = [];
    this.nicksEditores = [];
    this.nombresSagas = [];
    this.etiquetas = [];

    this.entryUrl = this.route.snapshot.paramMap.get('url');
    if (this.entryUrl) {
      this.route.data.subscribe(data => {
        if (this.log) {
          console.log('Init info general');
        }
        const libros = data.generalData.titulosLibros;
        libros.forEach((libro: string) => {
          this.titulosLibros.push({ label: ' ' + libro, value: libro });
        });
        const sagas = data.generalData.sagas;
        sagas.forEach((saga: string) => {
          this.nombresSagas.push({ label: ' ' + saga, value: saga });
        });
        const editores = data.generalData.nicksEditores;
        editores.forEach((editor: string) => {
          this.nicksEditores.push({ label: ' ' + editor, value: editor });
        });
        this.entrada = data.entrada;
        this.filas = Array();
        const texto = data.entrada.contenidoEntrada;
        const filas = $(texto);
        if (texto.indexOf('class="row') !== -1) {
          if (filas !== null && filas.length > 0) {
            for (let i = 0; i < filas.length; i++) {
              let filaT: Fila;
              const fila = filas[i];
              const columnas = $(fila.children);
              const numeroColumnas = columnas.length;
              if (columnas !== null && numeroColumnas > 0) {
                filaT = new Fila(i, $(columnas[0]).html());
                if (
                  $(fila)
                    .attr('class')
                    .indexOf('light-wrapper') !== -1
                ) {
                  filaT.colorFondo = 1;
                } else {
                  filaT.colorFondo = 2;
                }
                const anchura = $(columnas[0])
                  .attr('class')
                  .substring(7);
                filaT.columnas[0].anchura = anchura;
                filaT.columnas[0].bootstrapcolumn = 'col-sm-' + anchura;
                if (numeroColumnas > 1) {
                  for (let j = 1; j < numeroColumnas; j++) {
                    const columnaT = new Columna(j, $(columnas[j]).html());
                    const anchuraColumna = $(columnas[j])
                      .attr('class')
                      .substring(7);
                    columnaT.anchura = anchuraColumna;
                    columnaT.bootstrapcolumn = 'col-sm-' + anchuraColumna;
                    filaT.columnas.push(columnaT);
                    filaT.numeroColumnas++;
                  }
                }
              }
              // this.modificarFilaPorNumeroColumnas(filaT);
              if (filaT != null) {
                this.filas.push(filaT);
              }
            }
          }
        } else {
          let filaT: Fila;
          filaT = new Fila(0, texto);
          this.modificarFilaPorNumeroColumnas(filaT);
          this.filas.push(filaT);
        }

        const that = this;

        setTimeout(function() {
          return that.crearEditoresAsync();
        }, 100);

        data.entrada.etiquetas.forEach((etiqueta: Etiqueta) => {
          this.etiquetas.push(etiqueta.nombreEtiqueta);
        });
        this.date = new Date(data.entrada.publishDate);
        this.entrada.entryType
      });
    } else {
      this.route.data.subscribe(data => {
        if (this.log) {
          console.log('Init info general');
        }
        const libros = data.generalData.titulosLibros;
        libros.forEach((libro: string) => {
          this.titulosLibros.push({ label: ' ' + libro, value: libro });
        });
        const sagas = data.generalData.sagas;
        sagas.forEach((saga: string) => {
          this.nombresSagas.push({ label: ' ' + saga, value: saga });
        });
        const editores = data.generalData.nicksEditores;
        editores.forEach((editor: string) => {
          this.nicksEditores.push({ label: ' ' + editor, value: editor });
        });
      });
      this.entrada = new Entrada();
      this.entrada.etiquetas = [];
      this.entrada.contenidoEntrada = '';
      this.entrada.permitirComentarios = true;
      this.entrada.conSidebar = true;
      this.entrada.editorNombre = 'La Insomne';
      this.entrada.entryType = EntryTypeEnum.NEWS;
      this.date = new Date();
      const filas = new Array();
      const fila = new Fila(0, '');
      filas.push(fila);
      this.filas = filas;
      this.crearEditorAsync('editor-0-0', this.entrada.contenidoEntrada, 0, 0);
    }

    this.es = {
      firstDayOfWeek: 1,
      dayNames: [
        'domingo',
        'lunes',
        'martes',
        'miércoles',
        'jueves',
        'viernes',
        'sábado'
      ],
      dayNamesShort: ['dom', 'lun', 'mar', 'mié', 'jue', 'vie', 'sáb'],
      dayNamesMin: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
      monthNames: [
        'enero',
        'febrero',
        'marzo',
        'abril',
        'mayo',
        'junio',
        'julio',
        'agosto',
        'septiembre',
        'octubre',
        'noviembre',
        'diciembre'
      ],
      monthNamesShort: [
        'ene',
        'feb',
        'mar',
        'abr',
        'may',
        'jun',
        'jul',
        'ago',
        'sep',
        'oct',
        'nov',
        'dic'
      ],
      today: 'Hoy',
      clear: 'Borrar'
    };

    const today = new Date();
    const month = today.getMonth();
    const year = today.getFullYear();
    const prevMonth = month === 0 ? 11 : month - 1;
    const prevYear = prevMonth === 11 ? year - 1 : year;
    const nextMonth = month === 11 ? 0 : month + 1;
    const nextYear = nextMonth === 0 ? year + 1 : year;
  }

  ngAfterViewInit(): void {}

  cambioTitulo(newValue: string) {
    if (!this.customURL) {
      this.entrada.urlEntrada = encodeURIComponent(
        this.util.convertToSlug(newValue)
      );
    }
  }

  cambioNombreMenu(newValue: string) {
    if (!this.customURL) {
      this.entrada.urlMenuLibro = encodeURIComponent(
        this.util.convertToSlug(newValue)
      );
    }
  }

  urlChange(newValue: string) {
    this.customURL = true;
  }

  urlZonaChange(newValue: string) {
    this.customZona = true;
  }

  actualizarContenido(contenido: string) {
    this.entrada.contenidoEntrada = contenido;
  }

  actualizarResumen(resumen: string) {
    this.entrada.resumenEntrada = resumen;
  }

  showSuccess(mensaje: string) {
    if (this.log) {
      console.log(mensaje);
    }
    this.messageService.add({
      severity: 'success',
      summary: 'OK',
      detail: mensaje
    });
  }

  showError(mensaje: string[]) {
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
    this.messageService.add({
      severity: 'error',
      summary: 'ERROR',
      detail: mensajeTotal
    });
  }

  fileChange($event): void {
    this.fileUploadService.fileChange($event, 'imagenes-destacadas').subscribe(
      urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        const partesURL = urlImagenNueva.split('/');
        const partes = partesURL[partesURL.length - 1].split('.');
        const urlImagen =
          this.urlImageServer +
          'imagenes-destacadas/' +
          partes[0] +
          '.' +
          partes[1];

        this.showSuccess('Imagen guardada correctamente');
        this.entrada.imagenDestacada = urlImagen;
      },
      err => {
        // Log errors if any
        if (this.log) {
          console.log(err);
        }
      }
    );
  }

  guardarEntrada(): void {
    this.entrada.etiquetas = [];
    let texto = '';
    if (this.filas != null && this.filas.length > 0) {
      this.filas.forEach(fila => {
        let classFondo: string;
        if (fila.colorFondo === 1) {
          classFondo = 'light-wrapper';
        } else {
          classFondo = 'dark-wrapper';
        }

        texto += '<div class="row ' + classFondo + ' ">';

        if (fila.columnas != null && fila.columnas.length > 0) {
          fila.columnas.forEach(columna => {
            texto += '<div class="' + columna.bootstrapcolumn + '">';
            const textoEditor = <HTMLElement>(
              document.getElementById(
                'editor-' + fila.numFila + '-' + columna.numcolumna
              ).firstChild
            );
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
    let entryType = 'NEWS';
    if (this.entrada.entryType !== 0) {
      entryType = this.entrada.entryType.toString();
    }
    if (this.entrada.entryStatus == null) {
      this.entrada.entryStatus = EntryStatusEnum.DRAFT;
    }
    this.entrada.fechaAlta = this.date;
    const saveEntryRequest: SaveEntryRequest = {
      entradaId: this.entrada.entradaId,
      tituloEntrada: this.entrada.tituloEntrada,
      urlEntrada: this.entrada.urlEntrada,
      etiquetas: this.entrada.etiquetas,
      titulosLibrosEntrada: this.entrada.titulosLibrosEntrada,
      entryType: entryType,
      nombresSagasEntrada: this.entrada.nombresSagasEntrada,
      editorNombre: this.entrada.editorNombre,
      imagenDestacada: this.entrada.imagenDestacada,
      permitirComentarios: this.entrada.permitirComentarios,
      conSidebar: this.entrada.conSidebar,
      enMenu: this.entrada.enMenu,
      nombreMenuLibro: this.entrada.nombreMenuLibro,
      urlMenuLibro: this.entrada.urlMenuLibro,
      
      contenidoEntrada: this.entrada.contenidoEntrada,
      resumenEntrada: this.entrada.resumenEntrada,
      fraseDescriptiva: this.entrada.fraseDescriptiva,
      urlVideo: this.entrada.urlVideo,
      
      entryStatusId: 2,
      publishDate: this.date,
    }
    this.entryService.saveEntry(saveEntryRequest).subscribe(res => {
      if (res.estadoGuardado === 'CORRECTO') {
        this.showSuccess('Entrada guardada correctamente');
        this.router.navigate(['/gestion/lista-entradas']);
      } else {
        this.showError(res.listaErroresValidacion);
      }
    });
  }

  buscarGalerias(): void {
    this.galeriaService.getGalerias().subscribe(galerias => {
      this.galerias = galerias;
      galerias.forEach(galeria => {
        this.nombresGalerias.push({
          label: ' ' + galeria.nombreGaleria,
          value: galeria.urlGaleria
        });
      });
    });
  }

  esTipoVideo(): boolean {
    return this.entrada.entryType === EntryTypeEnum.VIDEO;
  }

  esMenu(): boolean {
    return this.entrada.enMenu;
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
        const editor = $('#editor-0-0').data('quill');

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
    this.filas.push(nuevaFila);
    this.crearEditorAsync(
      'editor-' + numFila + '-' + 0,
      '',
      nuevaFila.numFila,
      0
    );
  }

  crearEditorAsync(
    id: string,
    contenidoEntrada: string,
    numeroFila: number,
    numeroColumna: number
  ): void {
    this.idEditor = id;
    this.contenidoEntrada = contenidoEntrada;
    this.numeroFila = numeroFila;
    this.numeroColumna = numeroColumna;
    console.log('async');
    // tslint:disable-next-line:no-shadowed-variable
    const that = this;
    setTimeout(function() {
      return that.crearEditor();
    }, 100);
  }

  crearEditor(): void {
    if (this.log) {
      console.log('Quill');
    }

    const container = document.getElementById(this.idEditor);
    const toolbarOptions = [
      ['bold', 'italic', 'underline', 'strike'], // toggled buttons
      ['blockquote', 'code-block'],

      [{ header: 1 }, { header: 2 }], // custom button values
      [{ list: 'ordered' }, { list: 'bullet' }],
      [{ script: 'sub' }, { script: 'super' }], // superscript/subscript
      [{ indent: '-1' }, { indent: '+1' }], // outdent/indent
      [{ direction: 'rtl' }], // text direction

      [{ size: ['small', false, 'large', 'huge'] }], // custom dropdown
      [{ header: [1, 2, 3, 4, 5, 6, false] }],

      [{ color: [] }, { background: [] }], // dropdown with defaults from theme
      [{ font: [] }],
      [{ align: [] }],
      ['link', 'image', 'video', 'formula']['clean'],
      ['omega'] // remove formatting button
    ];

    const editor = new Quill(container, {
      theme: 'snow',
      modules: {
        toolbar:
          '#toolbar-container-' + this.numeroFila + '-' + this.numeroColumna,
        imageResize: {}
      }
    });

    const toolbar = editor.getModule('toolbar');
    toolbar.addHandler('omega', function() {
      console.log('omega');
    });

    const customButton = document.querySelector(
      '.ql-omega-' + this.numeroFila + '-' + this.numeroColumna
    );
    this.addCustomButtonBehaviour(customButton, editor);

    editor.pasteHTML(this.contenidoEntrada);
    editor.on('text-change', function(delta, oldDelta, source) {
      if (this.log) {
        console.log(editor.getContents());
      }
    });
    $(container).data('quill', editor);
  }

  private addCustomButtonBehaviour(customButton: Element, editor: any) {  
    customButton.addEventListener('click', function () {
      this.bookTemplateRange = editor.getSelection();
      this.bookTemplateEditor = editor;
      $('#bookTemplateModal').modal();
    });
  }

  getEditorData(editor: any){
    this.bookTemplateRange = editor.getSelection();
    this.bookTemplateEditor = editor;
  }

  addBookTemplateToEditor(data: any){
    if (this.bookTemplateRange) {
      // tslint:disable-next-line:max-line-length
      this.bookTemplateEditor.insertText(this.bookTemplateRange.index, '[momoko-libro titulo="'+ this.selectedBookTemplate +'"]');
    }
    this.bookTemplateRange = null;
    this.bookTemplateEditor = null;
    this.selectedBookTemplate = null;
    $('#bookTemplateModal').modal('hide');
  }

  pintarColumnas(event: Event, fila: Fila) {
    if (this.log) {
      console.log('Pintar columnas');
    }
    const numColumna = fila.numeroColumnas - 1;
    const numeroColumnasAntes = fila.columnas.length;
    if (fila.numeroColumnas < numeroColumnasAntes) {
      fila.columnas.splice(fila.numeroColumnas);
      this.modificarFilaPorNumeroColumnas(fila);
    } else {
      const columna = new Columna(numColumna, '');
      fila.columnas.push(columna);
      console.log('Anadir Columna');

      this.modificarFilaPorNumeroColumnas(fila);

      this.crearEditorAsync(
        'editor-' + fila.numFila + '-' + numColumna,
        '',
        fila.numFila,
        numColumna
      );
    }
  }

  modificarFilaPorNumeroColumnas(fila: Fila): void {
    switch (fila.numeroColumnas) {
      case 1:
        fila.columnas.forEach(columna => {
          columna.bootstrapcolumn = 'col-sm-12';
          columna.anchura = 12;
        });
        break;
      case 2:
        fila.columnas.forEach(columna => {
          columna.bootstrapcolumn = 'col-sm-6';
          columna.anchura = 6;
        });
        break;
      case 3:
        fila.columnas.forEach(columna => {
          columna.bootstrapcolumn = 'col-sm-4';
          columna.anchura = 4;
        });
        break;
      case 4:
        fila.columnas.forEach(columna => {
          columna.bootstrapcolumn = 'col-sm-3';
          columna.anchura = 3;
        });
        break;
      case 5:
        fila.columnas.forEach(columna => {
          columna.bootstrapcolumn = 'col-sm-2';
          columna.anchura = 2;
        });
        break;
      case 6:
        fila.columnas.forEach(columna => {
          columna.bootstrapcolumn = 'col-sm-2';
          columna.anchura = 2;
        });
        break;
      default:
        fila.columnas.forEach(columna => {
          columna.bootstrapcolumn = 'col-sm-12';
          columna.anchura = 12;
        });
    }
  }

  cambiarAnchura(event: Event, columna: Columna) {
    const col = 'col-sm-' + columna.anchura;
    columna.bootstrapcolumn = col;
  }

  crearEditoresAsync(): void {
    if (this.filas !== null && this.filas.length > 0) {
      this.filas.forEach(fila => {
        if (fila.columnas !== null && fila.columnas.length > 0) {
          fila.columnas.forEach(columna => {
            this.crearEditorConTexto(
              'editor-' + fila.numFila + '-' + columna.numcolumna,
              columna.texto,
              fila.numFila,
              columna.numcolumna
            );
          });
        }
      });
    }
  }

  crearEditorConTexto(
    idEditor: string,
    texto: string,
    numFila: number,
    numColumna: number
  ): void {
    if (this.log) {
      console.log('Quill');
    }

    const container = document.getElementById(idEditor);
    const toolbarOptions = [
      ['bold', 'italic', 'underline', 'strike'], // toggled buttons
      ['blockquote', 'code-block'],

      [{ header: 1 }, { header: 2 }], // custom button values
      [{ list: 'ordered' }, { list: 'bullet' }],
      [{ script: 'sub' }, { script: 'super' }], // superscript/subscript
      [{ indent: '-1' }, { indent: '+1' }], // outdent/indent
      [{ direction: 'rtl' }], // text direction

      [{ size: ['small', false, 'large', 'huge'] }], // custom dropdown
      [{ header: [1, 2, 3, 4, 5, 6, false] }],

      [{ color: [] }, { background: [] }], // dropdown with defaults from theme
      [{ font: [] }],
      [{ align: [] }],
      ['link', 'image', 'video', 'formula']['clean'],
      ['omega'] // remove formatting button
    ];

    const editor = new Quill(container, {
      theme: 'snow',
      modules: {
        toolbar: '#toolbar-container-' + numFila + '-' + numColumna,
        imageResize: {}
      }
    });

    const toolbar = editor.getModule('toolbar');
    toolbar.addHandler('omega', function() {
      console.log('omega');
    });

    const customButton = document.querySelector(
      '.ql-omega-' + numFila + '-' + numColumna
    );
    this.addCustomButtonBehaviour(customButton, editor);
    editor.pasteHTML(texto);
    $(container).data('quill', editor);
  }

  volver() {
    this.router.navigate(['/gestion/lista-entradas']);
  }

  changeEditorData(event: any){
    console.log('changeEditorData '  + event);
    const editor =$(event).data('quill');
    this.bookTemplateRange = editor.getSelection();
    this.bookTemplateEditor = editor;
  }
}
