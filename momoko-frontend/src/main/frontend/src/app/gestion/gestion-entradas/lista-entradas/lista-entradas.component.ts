import { Etiqueta } from 'app/dtos/etiqueta';
import { EntradaDetailComponent } from './../entrada-detail/entrada-detail.component';
import { Entrada } from 'app/dtos/entrada';
import { Component, OnInit, ViewChild } from '@angular/core';

import { EntradaService } from 'app/services/entrada.service';
import { FileUploadService } from 'app/services/fileUpload.service';
import { EntradaSimple } from 'app/dtos/entradaSimple';

declare var Quill: any;
declare var $: any;

import { ImageResize } from 'quill-image-resize-module';
import { environment } from 'environments/environment';
import { Fila } from 'app/gestion/gestion-entradas/fila';
import { Columna } from 'app/gestion/gestion-entradas/columna';
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
  selector: 'app-lista-entradas',
  templateUrl: './lista-entradas.component.html',
  styleUrls: ['./lista-entradas.component.css']
})
export class ListaEntradasComponent implements OnInit {

  private log = environment.log;

  loading: boolean;

  title = 'Libros';
  entradas: EntradaSimple[];
  selectedEntrada: Entrada;

  @ViewChild(EntradaDetailComponent) entradaDetailComponent: EntradaDetailComponent;

  constructor(private entradasService: EntradaService, private fileUploadService: FileUploadService) {
    this.entradas = [];

  }

  getEntradas(): void {
    if (this.log) {
      console.log('service -> getEntradas()');
    }
    this.entradas = [];
    this.entradasService.getAllEntradas().subscribe(entradas => {
      entradas.forEach(entrada => {
        this.entradas = [...this.entradas, entrada];
      });

    });
  }

  ngOnInit(): void {
    if (this.log) {
      console.log('ngOnInit Lista entradas');
    }
    this.loading = true;
    this.entradas = [];
    this.entradasService.getAllEntradas().subscribe(entradas => {
      const entradasList = entradas
      entradasList.forEach(entrada => {
        this.entradas = [...this.entradas, entrada];
      });
      this.loading = false;
    });
  }

  handleRowSelect(event: any) {
    if (this.log) {
      console.log('Seleccionando entrada');
    }
    let entrada: Entrada;

    entrada = event.data;
    this.entradaDetailComponent.etiquetas = [];
    this.entradasService.getEntradaAdmin(entrada.urlEntrada).subscribe(entradaCompleta => {
      this.entradaDetailComponent.filas = Array();
      if (this.log) {
        console.log(entradaCompleta);
      }
      this.selectedEntrada = entradaCompleta;
      const texto = this.selectedEntrada.contenidoEntrada;
      const filas = $(texto);

      if (filas !== null && filas.length > 0) {
        for (let i = 0; i < filas.length; i++) {
          let filaT: Fila;
          const fila = filas[i];
          const columnas = $(fila.children);
          const numeroColumnas = columnas.length;
          if (columnas !== null && numeroColumnas > 0) {
            filaT = new Fila(i, $(columnas[0]).html());
            if (numeroColumnas > 1) {
              for (let j = 1; j < numeroColumnas; j++) {
                const columnaT = new Columna(j, $(columnas[j]).html());
                filaT.columnas.push(columnaT);
                filaT.numeroColumnas++;
               }
            }
          }
          this.modificarFilaPorNumeroColumnas(filaT);
          if (filaT != null) {
            this.entradaDetailComponent.filas.push(filaT);
          }
        }
      }

      const that = this;

      setTimeout(function () {
        return that.entradaDetailComponent.crearEditoresAsync();
      }, 100);


      entradaCompleta.etiquetas.forEach((etiqueta: Etiqueta) => {
        this.entradaDetailComponent.etiquetas.push(etiqueta.nombreEtiqueta);
      });
      this.entradaDetailComponent.date = new Date(entradaCompleta.fechaAlta);
    });
    if (this.log) {
      console.log(entrada);
    }
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

  selectEntrada(entrada: Entrada) {
    if (this.log) {
      console.log(entrada);
    }
    this.selectedEntrada = entrada;
  }

  nuevaEntrada(): void {
    if (this.log) {
      console.log('Nueva entrada');
    }
    this.selectedEntrada = null;
    const entrada = new Entrada;
    entrada.etiquetas = [];
    entrada.contenidoEntrada = '';
    entrada.permitirComentarios = true;
    entrada.editorNombre = 'La Insomne';
    this.selectedEntrada = entrada;
    this.entradaDetailComponent.date = new Date();
    const filas = new Array();
    const fila = new Fila(0, '');
    fila.bootstrapcolumn = 'col-sm-12';
    filas.push(fila);
    this.entradaDetailComponent.filas = filas;
    this.entradaDetailComponent.crearEditorAsync('editor-0-0', this.selectedEntrada.contenidoEntrada, 0, 0);
  }

  volver(): void {
    this.selectedEntrada = null;
  }

  actualizarOAnadirEntrada(entrada: Entrada): void {
    this.selectedEntrada = null;
    this.entradas = [];
    this.getEntradas();
    if (this.log) {
      console.log(entrada);
    }
  }

}
