
import { Component, OnInit, Input, Output, EventEmitter, OnChanges, SimpleChange } from '@angular/core';
import { environment } from 'environments/environment';

import { Message } from 'primeng/components/common/api';

import { GrowlModule } from 'primeng/primeng';

import { SelectItem } from 'primeng/components/common/selectitem';
import { Saga } from '../../../../dtos/saga';
import { Libro } from '../../../../dtos/libro';
import { UtilService } from '../../../../services/util/util.service';
import { LibroService } from '../../../../services/libro.service';
import { SagaService } from '../../../../services/saga.service';
import { FileUploadService } from '../../services/file-upload.service';


@Component({
  selector: 'app-saga-detail',
  templateUrl: './saga-detail.component.html',
  styleUrls: ['./saga-detail.component.css']
})
export class SagaDetailComponent implements OnInit, OnChanges {

  private log = environment.log;

  @Input() saga: Saga;

  @Output() onSagaGuardada: EventEmitter<Saga> = new EventEmitter<Saga>();

  msgs: Message[] = [];

  libros: Libro[];
  mapaLibros: Libro[];

  librosOrdenados: Libro[];

  customURL = false;

  esSagaNueva = false;

  tiposSaga: SelectItem[];

  urlImageServer = environment.urlFiles;

  constructor(
    private util: UtilService,
    private libroService: LibroService,
    private sagaService: SagaService,
    private fileUploadService: FileUploadService,
  ) { }

  ngOnInit() {
    this.librosOrdenados = [];
    this.mapaLibros = [];
    this.tiposSaga = [];
    this.tiposSaga.push({ label: 'Saga', value: 'Saga' });
    this.tiposSaga.push({ label: 'Colección', value: 'Coleccion' });
    this.libroService.getLibros().then(libros => {
      if (this.log) {
        console.log('Obtener libros para sagas');
      }
      this.libros = libros;
      libros.forEach(libro => {
        this.mapaLibros[libro.urlLibro] = libro;
      });
    },
      error => {
        if (this.log) {
          console.log('Error al obtener libros para sagas ', error);
        }
      });

  }


  ngOnChanges(changes: { [propKey: string]: SimpleChange }) {
    const log: string[] = [];
    for (const propName of Object.keys(changes)) {
      const changedProp = changes[propName];
      const to = JSON.stringify(changedProp.currentValue);
      console.log('cambio saga');
      if (changedProp.isFirstChange()) {
        log.push(`Initial value of ${propName} set to ${to}`);
      } else {
        const from = JSON.stringify(changedProp.previousValue);
        log.push(`${propName} changed from ${from} to ${to}`);
        if (changedProp.currentValue != null) {
          const s: Saga = changedProp.currentValue;
          this.librosOrdenados = [];
          if (s.sagaId != null) {
            this.esSagaNueva = false;
            s.urlsLibrosSaga.forEach(urllibro => {
              const datosLibro = this.mapaLibros[urllibro];
              this.librosOrdenados.push(datosLibro);
              this.librosOrdenados.sort(function (a, b) {
                if (a.ordenSaga < b.ordenSaga) {
                  return -1;
                } else if (a.ordenSaga > b.ordenSaga) {
                  return 1;
                } else {
                  return 0;
                }
              })
            });
          } else {
            this.esSagaNueva = true;
          }
        }
      }
    }
  }

  urlChange(newValue: string) {
    this.customURL = true;
  }

  cambioNombre(newValue: string) {
    if (!this.customURL) {
      this.saga.urlSaga = encodeURIComponent(this.util.convertToSlug(newValue));
    }
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
    this.fileUploadService.fileChange($event, 'sagas').subscribe
      (urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        const partesURL = urlImagenNueva.split('/');
        const partes = partesURL[partesURL.length - 1].split('.');
        const urlImagen = this.urlImageServer + 'sagas/' + this.util.convertToSlug(partes[0]) + '.' + partes[1];

        this.showSuccess('Imagen guardada correctamente');
        this.saga.imagenSaga = urlImagen;
      },
      err => {
        // Log errors if any
        if (this.log) {
          console.log(err);
        }
      });
  }

  guardarSaga(): void {
    if (this.librosOrdenados.length > 0) {
      this.saga.librosSaga = [];
      this.saga.urlsLibrosSaga = [];
      this.librosOrdenados.forEach(libro => {
        this.saga.urlsLibrosSaga.push(libro.urlLibro);
      });
      this.sagaService.guardarSaga(this.saga)
        .subscribe(res => {
          if (res.estadoGuardado === 'CORRECTO') {
            this.showSuccess('Saga guardada correctamente');
            this.onSagaGuardada.emit(this.saga);
          } else {
            this.showError(res.listaErroresValidacion);
          }
        })
    } else {
      const error = [];
      error.push('No hay libros para la saga');
      this.showError(error);
    }

  }

}
