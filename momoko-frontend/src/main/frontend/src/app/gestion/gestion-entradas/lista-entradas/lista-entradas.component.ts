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
      if (this.log) {
        console.log(entradaCompleta);
      }
      this.selectedEntrada = entradaCompleta;
      if (this.log) {
        console.log('Quill');
      }
      const container = document.getElementById('editor');
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
        ['clean']                                         // remove formatting button
      ];

      const editor = new Quill(container, {
        theme: 'snow',
        modules: {
          toolbar: '#toolbar-container',
          imageResize: {}
        }
      });
      editor.pasteHTML(this.selectedEntrada.contenidoEntrada);
      editor.on('text-change', function (delta, oldDelta, source) {
        if (this.log) {
          console.log(editor.getContents());
        }
      });
      entradaCompleta.etiquetas.forEach((etiqueta: Etiqueta) => {
        this.entradaDetailComponent.etiquetas.push(etiqueta.nombreEtiqueta);
      });
      this.entradaDetailComponent.date = new Date(entradaCompleta.fechaAlta);

      $(container).data('quill', editor);
    });
    if (this.log) {
      console.log(entrada);
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
    setTimeout(function () {
      if (this.log) {
        console.log('Quill');
      }
      const container = document.getElementById('editor');
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
        ['clean']                                         // remove formatting button
      ];

      const editor = new Quill(container, {
        theme: 'snow',
        modules: {
          toolbar: '#toolbar-container',
          imageResize: {}
        }
      });
      editor.pasteHTML(this.selectedEntrada.contenidoEntrada);
      editor.on('text-change', function (delta, oldDelta, source) {
        if (this.log) {
          console.log(editor.getContents());
        }
      });
      $(container).data('quill', editor);
    }, 1000);
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
