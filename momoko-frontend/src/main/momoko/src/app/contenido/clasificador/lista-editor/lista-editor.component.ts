import { Component, OnInit } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { Location } from '@angular/common';
import { ClasificadorService } from '../../../services/clasificador.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Title, Meta } from '@angular/platform-browser';
import { UtilService } from '../../../services/util/util.service';
import { ObtenerPaginaEditorResponse } from '../../../dtos/response/obtenerPaginaEditorResponse';
import { User } from '../../../dtos/user';

@Component({
  selector: 'app-lista-editor',
  templateUrl: './lista-editor.component.html',
  styleUrls: ['./lista-editor.component.css']
})
export class ListaEditorComponent implements OnInit {

  private log = environment.log;

  private url: string;

  suscriptor: any;

  entradasEditor: EntradaSimple[];

  redactor: User;

  enLista: boolean;

  numeroEntradas: number;

  numeroEntradasPagina = 9;

  numeroPaginas: number;

  numbers

  size: number = 0;
  offset: number = 0;
  limit: number = 20;
  range: number = 3;

  paginaActual: number;

  constructor(private clasificadorService: ClasificadorService, private location: Location, private route: ActivatedRoute,
    private router: Router, private titleService: Title, private metaService: Meta, private util: UtilService) { }

  ngOnInit() {
    if (this.log) {
      console.log('Creando pagina de la editor');
    }
    this.enLista = false;
    this.suscriptor = this.route.params.subscribe(params => {
      this.url = params['url_editor']; // (+) converts string 'id' to a number
      if (params['numero_pagina'] != null) {
        this.paginaActual = params['numero_pagina'];
      } else {
        this.paginaActual = 1;
      }

      if (this.log) {
        console.log(this.url);
      }
      this.route.data.subscribe((data: { redactor: ObtenerPaginaEditorResponse }) => {
        this.entradasEditor = data.redactor.nueveEntradasEditor;
        this.redactor = data.redactor.autor;
        this.size = data.redactor.numeroEntradas;
        this.offset = 1;
        this.limit = this.numeroEntradasPagina;
        this.range = 2;

        this.util.removeAllTags(this.metaService);

        const metatituloPagina = 'Editor: ' + this.redactor.nombre + ': Últimas entradas en momoko del editor: ' +
          this.redactor.nombre;

        this.titleService.setTitle('Momoko - Editor: ' + this.redactor.nombre);
        const tag = {
          name: 'description', content: metatituloPagina
        };
        this.metaService.addTag(tag, false);



      });



      // In a real app: dispatch action to load the details here.
    });
  }

  ngOnDestroy() {
    this.suscriptor.unsubscribe();
  }

  onChangeOrder(event) {
    if (this.log) {
      console.log(event);
    }
  }

  activarEnLista() {
    this.enLista = true;
  }

  desactivarEnLista() {
    this.enLista = false;
  }

  onPageChange(offset) {
    this.offset = offset;

    const destinationPage: number = (offset / this.limit) + 1;
    if (this.log) {
      console.log("Pagina destino: " + destinationPage);
    }
    if (destinationPage != 1) {
      this.router.navigate(['/redactor/' + this.url + "/" + destinationPage]);
    } else {
      this.router.navigate(['/redactor/' + this.url]);
    }
  }

}
