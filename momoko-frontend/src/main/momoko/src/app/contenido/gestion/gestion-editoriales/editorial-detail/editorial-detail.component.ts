
import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  SimpleChange
} from '@angular/core';
import { Message } from 'primeng/primeng';

import { environment } from 'environments/environment';
import { Editorial } from '../../../../dtos/editorial';
import { EditorialService } from '../../services/editorial.service';
import { FileUploadService } from '../../services/file-upload.service';
import { GeneralDataService } from '../../services/general-data.service';
import { UtilService } from '../../../../services/util/util.service';


@Component({
  selector: 'app-editorial-detail',
  templateUrl: './editorial-detail.component.html',
  styleUrls: ['./editorial-detail.component.css']
})
export class EditorialDetailComponent implements OnInit {
  private log = environment.log;

  @Input() editorial: Editorial;

  @Output()
  onEditorialGuardado: EventEmitter<Editorial> = new EventEmitter<Editorial>();

  changeLog: string[] = [];

  esEditorialNuevo = false;

  msgs: Message[] = [];

  customURL = false;

  constructor(
    private editorialService: EditorialService,
    private fileUploadService: FileUploadService,
    private generalDataService: GeneralDataService,
    private util: UtilService
  ) {}

  ngOnInit(): void {
    this.generalDataService.getInformacionGeneral().subscribe(
      datos => {
        if (this.log) {
          console.log('Init info general');
        }
      },
      error => {
        if (this.log) {
          console.log('Error al recuperar los datos generales ', error);
        }
      }
    );
  }

  guardarEditorial(): void {
    this.editorialService.guardarEditorial(this.editorial).subscribe(res => {
      if (res.estadoGuardado === 'CORRECTO') {
        this.showSuccess('Editorial guardada correctamente');
        this.onEditorialGuardado.emit(this.editorial);
      } else {
        this.showError(['No se ha guardado correctamente la editorial']);
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
    this.fileUploadService
      .fileChange($event, 'cabeceras-editoriales')
      .subscribe(
        urlImagenNueva => {
          // Emit list event
          if (this.log) {
            console.log(urlImagenNueva);
          }
          this.showSuccess('Imagen guardada correctamente');
          this.editorial.imagenCabeceraEditorial = urlImagenNueva;
        },
        err => {
          // Log errors if any
          if (this.log) {
            console.log(err);
          }
        }
      );
  }

  fileChangeImagenEditorial($event): void {
    this.fileUploadService.fileChange($event, 'imagen-editorial').subscribe(
      urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        this.showSuccess('Imagen guardada correctamente');
        this.editorial.imagenEditorial = urlImagenNueva;
      },
      err => {
        // Log errors if any
        if (this.log) {
          console.log(err);
        }
      }
    );
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
    this.msgs.push({
      severity: 'error',
      summary: 'ERROR',
      detail: mensajeTotal
    });
  }

  urlChange(newValue: string) {
    this.customURL = true;
  }

  cambioNombre(newValue: string) {
    if (!this.customURL) {
      this.editorial.urlEditorial = encodeURIComponent(
        this.util.convertToSlug(newValue)
      );
    }
  }
}
