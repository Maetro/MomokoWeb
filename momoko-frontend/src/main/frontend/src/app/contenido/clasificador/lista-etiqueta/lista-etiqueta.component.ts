import { Component, OnInit, OnDestroy } from '@angular/core';
import { environment } from 'environments/environment';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { Etiqueta } from '../../../dtos/etiqueta';
import { ClasificadorService } from '../../../services/clasificador.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { Title, Meta } from '@angular/platform-browser';
import { ObtenerPaginaEtiquetaResponse } from '../../../dtos/response/obtenerPaginaEtiquetaResponse';

@Component({
  selector: 'app-lista-etiqueta',
  templateUrl: './lista-etiqueta.component.html',
  styleUrls: ['./lista-etiqueta.component.css']
})
export class ListaEtiquetaComponent implements OnInit, OnDestroy {

  private log = environment.log;

  private url: string;

  suscriptor: any;

  entradasEtiqueta: EntradaSimple[];

  etiqueta: Etiqueta;

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
      this.url = params['url_etiqueta']; // (+) converts string 'id' to a number
      if (this.log) {
        console.log(this.url);
      }
      this.route.data.subscribe((data: { paginaEtiquetaResponse: ObtenerPaginaEtiquetaResponse }) => {
        this.entradasEtiqueta = data.paginaEtiquetaResponse.entradasEtiqueta;
        this.etiqueta = data.paginaEtiquetaResponse.etiqueta;
        this.numeroEntradas = data.paginaEtiquetaResponse.numeroEntradas;
        this.numeroPaginas = Math.ceil(this.numeroEntradas / this.numeroEntradasPagina);
        this.numbers = Array(this.numeroPaginas).fill(0).map((x, i) => i + 1);

        const metatituloPagina = 'Últimas entradas en momoko sobre la etiqueta: ' +
          this.etiqueta.nombreEtiqueta;

        this.titleService.setTitle('Momoko - Etiqueta: ' + this.etiqueta.nombreEtiqueta);
        const tag = {
          name: 'description', content: metatituloPagina
        };
        const attributeSelector = 'name="description"';
        this.metaService.removeTag(attributeSelector);
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

  loadPage(numeroPagina: number) {
    if (this.log) {
      console.log('Cargando pagina ' + numeroPagina);
    }
    this.clasificadorService.getEtiquetaPage(this.url, numeroPagina).subscribe(
      (paginaCategoriaResponse: ObtenerPaginaEtiquetaResponse) => {
        this.entradasEtiqueta = paginaCategoriaResponse.entradasEtiqueta;
        this.etiqueta = paginaCategoriaResponse.etiqueta;
        this.numeroEntradas = paginaCategoriaResponse.numeroEntradas;
        this.numeroPaginas = Math.ceil(this.numeroEntradas / this.numeroEntradasPagina);
        this.numbers = Array(this.numeroPaginas).fill(0).map((x, i) => i + 1);
        const url = this.router.createUrlTree([{ param: 1 }], { relativeTo: this.route }).toString();
        this.location.replaceState('/tag/' + this.url + '/' + numeroPagina);
        window.scrollTo(0, 0);
      });
  }
}
