import { Component, OnInit, OnDestroy } from '@angular/core';
import { ObtenerPaginaCategoriaResponse } from '../../../dtos/response/obtenerPaginaCategoriaResponse';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { Categoria } from '../../../dtos/categoria';
import { ClasificadorService } from '../../../services/clasificador.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { environment } from 'environments/environment';
import { Title, Meta } from '@angular/platform-browser';

@Component({
  selector: 'app-lista-categoria',
  templateUrl: './lista-categoria.component.html',
  styleUrls: ['./lista-categoria.component.css']
})
export class ListaCategoriaComponent implements OnInit, OnDestroy {

  private log = environment.log;

  private url: string;

  suscriptor: any;

  entradasCategoria: EntradaSimple[];

  categoria: Categoria;

  enLista: boolean;

  numeroEntradas: number;

  numeroEntradasPagina = 9;

  numeroPaginas: number;

  numbers

  constructor(private clasificadorService: ClasificadorService, private location: Location, private route: ActivatedRoute,
    private router: Router, private titleService: Title, private metaService: Meta) { }

  ngOnInit() {
    if (this.log) {
      console.log('Creando pagina de la categoria');
    }
    this.enLista = false;
    this.suscriptor = this.route.params.subscribe(params => {
      this.url = params['url_categoria']; // (+) converts string 'id' to a number
      if (this.log) {
        console.log(this.url);
      }
      this.route.data.subscribe((data: { paginaCategoriaResponse: ObtenerPaginaCategoriaResponse }) => {
        this.entradasCategoria = data.paginaCategoriaResponse.entradasCategoria;
        this.categoria = data.paginaCategoriaResponse.categoria;
        this.numeroEntradas = data.paginaCategoriaResponse.numeroEntradas;
        this.numeroPaginas = Math.ceil(this.numeroEntradas / this.numeroEntradasPagina);
        this.numbers = Array(this.numeroPaginas).fill(0).map((x, i) => i + 1);

        if (this.categoria.urlCategoria === 'noticias') {
          this.titleService.setTitle('Momoko - Últimas noticias');
          const tag = {
            name: 'description', content: 'Últimas noticias de libros del mundo editorial en general: Rumores, ' +
              'estadísticas, fechas de lanzamiento y mucho más.'
          };
          const attributeSelector = 'name="description"';
          this.metaService.removeTag(attributeSelector);
          this.metaService.addTag(tag, false);
        } else if (this.categoria.urlCategoria === 'miscelaneos') {
          this.titleService.setTitle('Momoko - Últimas noticias');
          const tag = {
            name: 'description', content: 'Miscelaneos: Contendido con todo lo que rodea a tus libros favoritos, imágenes,' +
              ' eventos y curiosidades:'
          };
          const attributeSelector = 'name="description"';
          this.metaService.removeTag(attributeSelector);
          this.metaService.addTag(tag, false);

        } else if (this.categoria.urlCategoria === 'videos') {
          const metatituloPagina = '';
          this.titleService.setTitle('Momoko - Últimos vídeos');
          const tag = {
            name: 'description', content: 'Videos: Videos relacionados con el mundo de la literatura y sobre tus libros favoritos'
          };
          const attributeSelector = 'name="description"';
          this.metaService.removeTag(attributeSelector);
          this.metaService.addTag(tag, false);
        } else {
          const metatituloPagina = 'Categoría: ' + this.categoria.nombreCategoria + ': Últimas entradas en momoko sobre la categoría: ' +
            this.categoria.nombreCategoria;

          this.titleService.setTitle('Momoko - Categoría: ' + this.categoria.nombreCategoria);
          const tag = {
            name: 'description', content: metatituloPagina
          };
          const attributeSelector = 'name="description"';
          this.metaService.removeTag(attributeSelector);
          this.metaService.addTag(tag, false);
        }


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

  loadPage(numeroPagina: number) {
    if (this.log) {
      console.log('Cargando pagina ' + numeroPagina);
    }
    this.clasificadorService.getCategoriaPage(this.url, numeroPagina).subscribe(
      (paginaCategoriaResponse: ObtenerPaginaCategoriaResponse) => {
        this.entradasCategoria = paginaCategoriaResponse.entradasCategoria;
        this.categoria = paginaCategoriaResponse.categoria;
        this.numeroEntradas = paginaCategoriaResponse.numeroEntradas;
        this.numeroPaginas = Math.ceil(this.numeroEntradas / this.numeroEntradasPagina);
        this.numbers = Array(this.numeroPaginas).fill(0).map((x, i) => i + 1);
        const url = this.router.createUrlTree([{ param: 1 }], { relativeTo: this.route }).toString();
        this.location.replaceState('/categoria/' + this.url + '/' + numeroPagina);
        window.scrollTo(0, 0);
      });
  }

}
