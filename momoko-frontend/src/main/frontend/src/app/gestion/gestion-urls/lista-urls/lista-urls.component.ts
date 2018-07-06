import { Component, OnInit, ViewChild } from '@angular/core';
import { environment } from 'environments/environment';
import { EntradaUrl } from '../../../dtos/entradaurl';
import { ModificadorUrlsComponent } from '../modificador-urls/modificador-urls.component';
import { HerramientasService } from '../../../services/herramientas.service';

@Component({
  selector: 'app-lista-urls',
  templateUrl: './lista-urls.component.html',
  styleUrls: ['./lista-urls.component.css']
})
export class ListaUrlsComponent implements OnInit {
  private log = environment.log;

  loading: boolean;

  title = 'Libros';
  urls: EntradaUrl[];
  selectedUrl: EntradaUrl;

  @ViewChild(ModificadorUrlsComponent)
  modificadorUrlsComponent: ModificadorUrlsComponent;

  constructor(private herramientasService: HerramientasService) {
    if (this.log) {
      console.log('Builder ListaEditorialesComponent');
    }
    this.urls = [];
  }

  getUrlsEntradas(): void {
    if (this.log) {
      console.log('service -> getEditoriales()');
    }
    this.herramientasService.getUrlsEntradas().subscribe(urls => {
      urls.forEach(url => {
        this.urls = [...this.urls, url];
      });
    });
  }

  ngOnInit(): void {
    if (this.log) {
      console.log('ListaUrlsComponent');
    }
    this.loading = true;
    this.herramientasService.getUrlsEntradas().subscribe(urls => {
      urls.forEach(url => {
        this.urls = [...this.urls, url];
      });
      this.loading = false;
    });
  }

  selectUrlsEntradas(url: EntradaUrl) {
    if (this.log) {
      console.log('selectUrlsEntradas');
    }

    this.selectedUrl = url;
  }

  volver(): void {
    if (this.log) {
      console.log('volver');
    }
    this.selectedUrl = null;
  }

  // actualizarOAnadirEditorial(editorial: Editorial): void {
  //   if (this.log) {
  //     console.log('actualizarOAnadirEditorial ' + editorial);
  //   }
  //   this.selectedEditorial = null;
  //   let encontrado = false;
  //   this.editoriales.forEach(ed => {
  //     if (ed.nombreEditorial === editorial.nombreEditorial) {
  //       ed = editorial;
  //       encontrado = true;
  //     }
  //   });
  //   if (!encontrado) {
  //     this.editoriales = [...this.editoriales, editorial];
  //   }
  // }

  onRowSelect(event) {
    if (this.log) {
      console.log('onRowSelect');
    }
    if (!event.data.infoAdicional) {
      event.data.infoAdicional = new Array();
    }
  }
}
