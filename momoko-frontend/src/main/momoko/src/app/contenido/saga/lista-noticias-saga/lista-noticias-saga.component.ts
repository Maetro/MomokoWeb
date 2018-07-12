import { DatoEntrada } from '../../../dtos/datoEntrada';
import { Component, OnInit } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { ActivatedRoute, Router } from '@angular/router';
import { Title, Meta } from '@angular/platform-browser';
import { Saga } from '../../../dtos/saga';
import { ObtenerPaginaColeccionSagaResponse } from '../../../dtos/response/obtenerPaginaSagaColeccionResponse';


@Component({
  selector: 'app-lista-noticias-saga',
  templateUrl: './lista-noticias-saga.component.html',
  styleUrls: ['./lista-noticias-saga.component.css']
})
export class ListaNoticiasSagaComponent implements OnInit {
  private log = environment.log;

  saga: Saga;
  noticias: EntradaSimple[];
  numeroEntradas: number;
  enLista: boolean;
  datosEntrada: DatoEntrada[];
  urlAnalisis: string;

  constructor(
    private route: ActivatedRoute,
    private titleService: Title,
    private metaService: Meta
  ) {}

  ngOnInit() {

    this.enLista = false;
    this.route.data.subscribe(
      (coleccionSaga: {
        coleccionSaga: ObtenerPaginaColeccionSagaResponse;
      }) => {
        this.saga = coleccionSaga.coleccionSaga.saga;
        this.noticias = coleccionSaga.coleccionSaga.entradas;
        this.numeroEntradas = coleccionSaga.coleccionSaga.numeroEntradas;
        this.datosEntrada = coleccionSaga.coleccionSaga.datosEntrada;
        let autores = '';
        this.saga.autores.forEach(autor => {
          autores = autores + autor.nombre + ', ';
        });
        autores = autores.substring(0, autores.length - 2);
        const metatituloPagina =
          'Encuentra aquí las últimas noticias sobre ' +
          this.saga.nombreSaga +
          ' de ' +
          autores;
        this.titleService.setTitle(metatituloPagina);
        this.metaService.removeTag('name="og:url"');
        this.metaService.removeTag('name="og:type"');
        this.metaService.removeTag('name="og:title"');
        this.metaService.removeTag('name="og:description"');
        this.metaService.removeTag('name="og:image"');
        this.metaService.addTag({
          name: 'og:url',
          content: 'https://momoko.es/libro/' + this.saga.urlSaga + 'noticias'
        });
        this.metaService.addTag({ name: 'og:locale', content: 'es_ES' });
        this.metaService.addTag({
          name: 'fb:app_id',
          content: '1932678757049258'
        });
        this.metaService.addTag({ name: 'og:type', content: 'article' });
        this.metaService.addTag({
          name: 'og:title',
          content: this.saga.nombreSaga
        });
        this.metaService.addTag({
          name: 'og:description',
          content: this.saga.resumen
        });
        this.metaService.addTag({
          name: 'og:image',
          content: this.saga.imagenSaga
        });
      }
    );
  }

  obtenerUrlAnalisis(): string {
    let urlAnalisis;
    this.datosEntrada.some(datoEntrada => {
      if (datoEntrada.tipoEntrada === 2) {
        urlAnalisis = datoEntrada.urlEntrada;
        return true;
      }
    });
    return urlAnalisis;
  }

  activarEnLista() {
    this.enLista = true;
  }

  desactivarEnLista() {
    this.enLista = false;
  }
}
