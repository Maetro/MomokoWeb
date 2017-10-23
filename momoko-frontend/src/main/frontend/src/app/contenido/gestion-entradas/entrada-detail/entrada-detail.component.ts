import { Component, OnInit, Input, Output, EventEmitter, AfterViewInit, OnDestroy } from '@angular/core';
import { Entrada } from 'app/dtos/entrada';
import { EntradaService } from 'app/services/entrada.service';
import { FileUploadService } from 'app/services/fileUpload.service';
import { DropdownModule, CheckboxModule, Message, ChipsModule } from 'primeng/primeng';
import { GeneralDataService } from 'app/services/general-data.service';
import { SelectItem } from 'primeng/components/common/selectitem';


@Component({
  selector: 'app-entrada-detail',
  templateUrl: './entrada-detail.component.html',
  styleUrls: ['./entrada-detail.component.css']
})
export class EntradaDetailComponent implements OnInit {

  @Input() entrada: Entrada;

  @Output() onEntradaGuardada: EventEmitter<Entrada> = new EventEmitter<Entrada>();

  esLibroNuevo = true;

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
    this.tiposEntrada.push({ label: 'Análisis', value: 1 });
    this.tiposEntrada.push({ label: 'Noticia', value: 2 });
    this.estadosEntrada = [];
    this.estadosEntrada.push({ label: 'Borrador', value: 1 });
    this.estadosEntrada.push({ label: 'Publicada', value: 2 });
    this.estadosEntrada.push({ label: 'Borrada', value: 3 });
  }

  ngOnInit() {
    this.titulosLibros = [];

    this.esLibroNuevo = true;

    this.generalDataService.getInformacionGeneral().subscribe(datos => {
      console.log('Init info general');
      const libros = datos.titulosLibros;
      this.titulosLibros.push({ label: ' ' + 'No asociar', value: null });
      libros.forEach(libro => {
        this.titulosLibros.push({ label: ' ' + libro, value: libro });
      });
    },
      error => {
        console.log('Error al recuperar los datos generales ', error);
      });
  }

  cambioTitulo(newValue) {
    if (!this.customURL) {
      this.entrada.urlEntrada = encodeURIComponent(this.convertToSlug(newValue));
    }
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
    this.entradaService.guardarEntrada(this.entrada)
      .then(res => {
        this.showSuccess('Entrada guardada correctamente');
        this.onEntradaGuardada.emit(this.entrada);
      })
      .catch();
  }

}
