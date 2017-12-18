import { environment } from './../../../../environments/environment';
import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { Galeria } from 'app/dtos/galeria';
import { Message } from 'primeng/primeng';
import { GaleriaService } from 'app/services/galeria.service';
import { FileUploadService } from 'app/services/fileUpload.service';
import { UtilService } from 'app/services/util.service';
import { GeneralDataService } from 'app/services/general-data.service';

const NUM = 12;

@Component({
  selector: 'app-galeria-detail',
  templateUrl: './galeria-detail.component.html',
  styleUrls: ['./galeria-detail.component.css']
})
export class GaleriaDetailComponent implements OnInit {

  galeria: Galeria;

  @Input() set _galeria(value: Galeria) {
    this.galeria = value;
    if (value != null) {
      if (value.galeriaId != null) {
        this.numeroFilas = Math.ceil(value.imagenes.length / value.columnas);
      }
      this.pintarColumnas();
    }
  }

  @Output() onGaleriaGuardado: EventEmitter<Galeria> = new EventEmitter<Galeria>();


  changeLog: string[] = [];

  esGaleriaNuevo = false;

  msgs: Message[] = [];

  customURL = false;

  bootstrapcolumn = '';

  numeroFilas = 1;

  urlImageServer = environment.urlFiles;

  constructor(private galeriaService: GaleriaService, private fileUploadService: FileUploadService,
    private generalDataService: GeneralDataService, private util: UtilService) {
  }

  ngOnInit(): void {

    this.generalDataService.getInformacionGeneral().subscribe(datos => {
      console.log('Init info general');
    },
      error => {
        console.log('Error al recuperar los datos generales ', error);
      });
  }


  guardarGaleria(): void {
    this.galeriaService.guardarGaleria(this.galeria)
      .subscribe(res => {
        if (res.estadoGuardado === 'CORRECTO') {
          this.showSuccess('Género guardado correctamente');
          this.onGaleriaGuardado.emit(this.galeria);
        } else {
          this.showError(res.listaErroresValidacion);
        }
      });
  }

  showSuccess(mensaje: string) {
    this.msgs = [];
    console.log(mensaje);
    this.msgs.push({ severity: 'success', summary: 'OK', detail: mensaje });
  }

  fileChangeCabecera($event, $i): void {
    this.fileUploadService.fileChange($event, 'cabeceras-galerias').subscribe
      (urlImagenNueva => {
        // Emit list event
        console.log(urlImagenNueva);
        this.showSuccess('Imagen guardada correctamente');
        this.galeria.imagenes[$i] = urlImagenNueva;
      },
      err => {
        // Log errors if any
        console.log(err);
      });
  }

  multipleFileChangeCabecera($event): void {


    console.log('Subiendo bloque');
    let num = 0;
    $event.files.forEach(file => {
      const temp = {
        files: []
      }
      temp.files.push(file);
      console.log(file);
      this.fileChangeCabecera(temp, num);
      num++
    });
    console.log('Actualizando imagenes');
    this.anadirImagenesSubidasEnBloque($event);

  }
  //   this.fileUploadService.fileChange($event, 'cabeceras-galerias').subscribe
  //     (urlImagenNueva => {
  //       // Emit list event
  //       console.log(urlImagenNueva);
  //       this.showSuccess('Imagen guardada correctamente');
  //       this.anadirImagenesSubidasEnBloque($event);

  //     },
  //     err => {
  //       // Log errors if any
  //       console.log(err);
  //     });
  // }

  anadirImagenesSubidasEnBloque($event) {
    const numeroFicheros = $event.files.length;
    const numeroHuecos = this.calcularNumeroHuecos(this.galeria.imagenes);
    let $i = 0;
    if (numeroHuecos < numeroFicheros) {
      let $numeroFilas = this.numeroFilas;

      while (($numeroFilas * this.galeria.columnas) <  numeroFicheros) {
        $numeroFilas++;
      }
      this.numeroFilas = $numeroFilas;
    }
    $event.files.forEach(fichero => {
      const urlImagen = this.urlImageServer + 'cabeceras-galerias/' + fichero.name;
      if (this.galeria.imagenes[$i] == null || this.galeria.imagenes[$i] === '') {
        this.galeria.imagenes[$i] = urlImagen;
      }
      $i++;
    });

  }

  calcularNumeroHuecos(imagenes: string[]) {
    let numHuecos = 0;
    imagenes.forEach(imagen => {
      if (imagen == null || imagen === '') {
        numHuecos++;
      }
    });
    return numHuecos;
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

  urlChange(newValue: string) {
    this.customURL = true;
  }

  cambioNombre(newValue: string) {
    if (!this.customURL) {
      this.galeria.urlGaleria = encodeURIComponent(this.util.convertToSlug(newValue));
    }
  }

  pintarColumnas() {
    console.log('Pintar columnas');
    switch (this.galeria.columnas) {
      case 1:
        this.bootstrapcolumn = 'col-sm-12';
        break;
      case 2:
        this.bootstrapcolumn = 'col-sm-6';
        break;
      case 3:
        this.bootstrapcolumn = 'col-sm-4';
        break;
      case 4:
        this.bootstrapcolumn = 'col-sm-3';
        break;
      case 5:
        this.bootstrapcolumn = 'col-sm-2';
        break;
      case 6:
        this.bootstrapcolumn = 'col-sm-2';
        break;
      default:
        this.bootstrapcolumn = 'col-sm-12';
    }

    if ((this.galeria.columnas * this.numeroFilas) > this.galeria.imagenes.length) {
      for (let imagenNumero = this.galeria.imagenes.length; imagenNumero < (this.galeria.columnas * this.numeroFilas); imagenNumero++) {
        this.galeria.imagenes.push('');
      }
    } else {
      for (let imagenNumero = this.galeria.imagenes.length; imagenNumero > (this.galeria.columnas * this.numeroFilas); imagenNumero--) {
        this.galeria.imagenes.pop();
      }
    }
  }
}
