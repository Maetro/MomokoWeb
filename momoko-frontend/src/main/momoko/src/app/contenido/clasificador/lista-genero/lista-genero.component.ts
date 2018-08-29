import { OrderType } from '../../../dtos/enums/ordertype';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from '../../../../environments/environment';
import { Genero } from '../../../dtos/genre/genero';
import { LibroSimple } from '../../../dtos/libroSimple';
import { GenrePageResponse } from '../../../dtos/genre/genrePageResponse';
import { ClasificadorService } from '../../../services/clasificador.service';
import { UtilService } from '../../../services/util/util.service';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { Globals } from '../../../app.globals';
import { Filter } from '../../../dtos/filter/filter';
import { FilterFrontalService } from './filter-frontal.service';
import { take, map } from 'rxjs/operators';

@Component({
  selector: 'app-lista-genero',
  templateUrl: './lista-genero.component.html',
  styleUrls: ['./lista-genero.component.css']
})
export class ListaGeneroComponent implements OnInit, OnDestroy {
  private log = environment.log;

  private url: string;

  anchura: number;

  suscriptor: any;

  librosGenero: LibroSimple[];

  entradasPopulares: EntradaSimple[];

  genero: Genero;

  librosParecidos: LibroSimple[];

  filters: Filter[];

  tituloSeccionLibros = 'Otros libros parecidos';

  numeroEntradas: number;

  numeroEntradasPagina = 9;

  numeroPaginas: number;

  numbers;

  isMobile: boolean;

  enLista: boolean;

  orderby: OrderType;

  numeroPagina: number;

  constructor(
    private clasificadorService: ClasificadorService,
    private route: ActivatedRoute,
    private router: Router,
    private titleService: Title,
    private metaService: Meta,
    private globals: Globals,
    private util: UtilService,
    private filterFrontalService: FilterFrontalService
  ) {}

  ngOnInit() {
    if (this.log) {
      console.log('Creando pagina del genero');
    }
    this.enLista = true;
    this.orderby = this.globals.orderType;
    this.suscriptor = this.route.params.subscribe(params => {
      this.url = params['url_genero']; // (+) converts string 'id' to a number
      this.numeroPagina = params['numero_pagina'];
      if (!this.numeroPagina) {
        this.numeroPagina = 1;
      }
      if (this.log) {
        console.log(this.url);
      }
      this.route.data.subscribe(
        (data: { paginaGeneroResponse: GenrePageResponse }) => {
          this.genero = data.paginaGeneroResponse.genero;
          this.util.removeAllTags(this.metaService);
          this.librosGenero = data.paginaGeneroResponse.nueveLibrosGenero;
          this.entradasPopulares =
            data.paginaGeneroResponse.tresUltimasEntradasConLibro;
          this.filters = data.paginaGeneroResponse.applicableFilters;
          const metatituloPagina =
            'Aquí encontrarás críticas, reseñas, opiniones y análisis de los libros del género ' +
            this.genero.nombre +
            ' en momoko';
          const tag = {
            name: 'description',
            content:
              'Últimas fichas de los libros del género ' +
              this.genero.nombre +
              'desde donde podras acceder a sus noticias y análisis'
          };
          this.metaService.addTag(tag, false);
          this.titleService.setTitle(metatituloPagina);
          this.numeroEntradas = data.paginaGeneroResponse.numeroLibros;
          this.numeroPaginas = Math.ceil(
            this.numeroEntradas / this.numeroEntradasPagina
          );
          this.numbers = Array(this.numeroPaginas)
            .fill(0)
            .map((x, i) => i + 1);
        }
      );

      // const columna = document.getElementById('mirarAnchura0');
      // const width = columna.offsetWidth;
      // const style = window.getComputedStyle(columna);
      // // tslint:disable-next-line:radix
      // const margin = parseInt(style.paddingLeft) + parseInt(style.paddingRight);
      // this.anchura = width - margin;
      // In a real app: dispatch action to load the details here.
    });
  }

  activarEnLista(value: boolean) {
    this.enLista = value;
  }

  ngOnDestroy() {
    this.suscriptor.unsubscribe();
  }

  onChangeOrder(event) {
    if (this.log) {
      console.log(event);
    }
  }

  mirarAnchura(): number {
    return 236;
  }

  cambiarOrden() {
    this.globals.orderType = this.orderby;
    this.clasificadorService
      .getGenrePage(this.url, this.numeroPagina, this.orderby)
      .subscribe(generoPageResponse => {
        this.librosGenero = generoPageResponse.nueveLibrosGenero;
      });
  }

  updateFilters(event: Filter[]) {
    console.log('updateFilters: ' + event);

    let filtered = false;
    this.filters.some(filter => {
      if (filter.value && filter.value.length > 0) {
        filtered = true;
        return true;
      }
    });
    if (filtered) {
      this.filterFrontalService
        .applyFilters(this.genero.urlGenero, event)
        .subscribe(res => {
          this.filters = res.avaliableFiltersList;
          this.librosGenero = res.booksSelected;
        });
    } else {
      return this.clasificadorService
        .getGenrePage(this.url, this.numeroPagina, this.globals.orderType)
        .pipe(
          map(genero => {
            
              this.librosGenero = genero.nueveLibrosGenero;
              this.filters = genero.applicableFilters;
            
          })
        );
    }
  }
}
