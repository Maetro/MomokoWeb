import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { environment } from 'environments/environment';
import { Message } from 'primeng/primeng';
import { Redactor } from '../../../dtos/redactor';
import { FileUploadService } from '../../../services/fileUpload.service';
import { GeneralDataService } from '../../../services/general-data.service';
import { RedactorService } from '../../../services/redactor.service';
import { UtilService } from '../../../services/util.service';


@Component({
  selector: 'app-redactor-detail',
  templateUrl: './redactor-detail.component.html',
  styleUrls: ['./redactor-detail.component.css']
})
export class RedactorDetailComponent implements OnInit {

  private log = environment.log;

  @Input() redactor: Redactor;

  @Output() onRedactorGuardado: EventEmitter<Redactor> = new EventEmitter<Redactor>();

  changeLog: string[] = [];

  esRedactorNuevo = false;

  msgs: Message[] = [];

  customURL = false;

  constructor(private redactorService: RedactorService, private fileUploadService: FileUploadService,
    private generalDataService: GeneralDataService, private util: UtilService) {
  }

  ngOnInit(): void {
    this.generalDataService.getInformacionGeneral().subscribe(datos => {
      if (this.log) {
        console.log('Init info general');
      }
    },
      error => {
        if (this.log) {
          console.log('Error al recuperar los datos generales ', error);
        }
      });
  }


  guardarRedactor(): void {
    this.redactorService.guardarRedactor(this.redactor)
      .subscribe(res => {
        if (res.estadoGuardado === 'CORRECTO') {
          this.showSuccess('Género guardado correctamente');
          this.onRedactorGuardado.emit(this.redactor);
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
    this.fileUploadService.fileChange($event, 'cabeceras-redactores').subscribe
      (urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        this.showSuccess('Imagen guardada correctamente');
        this.redactor.imagenCabeceraRedactor = urlImagenNueva;
      },
      err => {
        // Log errors if any
        if (this.log) {
          console.log(err);
        }
      });
  }

  fileChangeAvatar($event): void {
    this.fileUploadService.fileChange($event, 'avatares').subscribe
      (urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        this.showSuccess('Imagen guardada correctamente');
        this.redactor.avatarRedactor = urlImagenNueva;
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
      this.redactor.urlRedactor = encodeURIComponent(this.util.convertToSlug(newValue));
    }
  }

}
