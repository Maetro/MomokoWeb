import { Component, OnInit, OnDestroy } from '@angular/core';
import { ObtenerPaginaCategoriaResponse } from 'app/dtos/response/obtenerPaginaCategoriaResponse';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { Categoria } from 'app/dtos/categoria';
import { ClasificadorService } from 'app/services/clasificador.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-lista-categoria',
  templateUrl: './lista-categoria.component.html',
  styleUrls: ['./lista-categoria.component.css']
})
export class ListaCategoriaComponent implements OnInit, OnDestroy {

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
       private router: Router) { }

    ngOnInit() {

      console.log('Creando pagina de la categoria')
      this.enLista = false;
      this.suscriptor = this.route.params.subscribe(params => {
        this.url = params['url_categoria']; // (+) converts string 'id' to a number
        console.log(this.url);

        this.route.data.subscribe((data: { paginaCategoriaResponse: ObtenerPaginaCategoriaResponse }) => {
          this.entradasCategoria = data.paginaCategoriaResponse.entradasCategoria;
          this.categoria = data.paginaCategoriaResponse.categoria;
          this.numeroEntradas = data.paginaCategoriaResponse.numeroEntradas;
          this.numeroPaginas = Math.ceil(this.numeroEntradas / this.numeroEntradasPagina);
          this.numbers = Array(this.numeroPaginas).fill(0).map((x, i) => i + 1);
        });



        // In a real app: dispatch action to load the details here.
      });
    }

    ngOnDestroy() {
      this.suscriptor.unsubscribe();
    }

    onChangeOrder(event) {
      console.log(event);
    }

    activarEnLista() {
      this.enLista = true;
    }

    desactivarEnLista() {
      this.enLista = false;
    }

    loadPage(numeroPagina: number) {
      console.log('Cargando pagina ' + numeroPagina);
      this.clasificadorService.getCategoriaPage(this.url, numeroPagina).subscribe(
        (paginaCategoriaResponse: ObtenerPaginaCategoriaResponse) => {
        this.entradasCategoria = paginaCategoriaResponse.entradasCategoria;
        this.categoria = paginaCategoriaResponse.categoria;
        this.numeroEntradas = paginaCategoriaResponse.numeroEntradas;
        this.numeroPaginas = Math.ceil(this.numeroEntradas / this.numeroEntradasPagina);
        this.numbers = Array(this.numeroPaginas).fill(0).map((x, i) => i + 1);
        const url = this.router.createUrlTree([{param: 1}], {relativeTo: this.route}).toString();
        this.location.replaceState('/categoria/' + this.url + '/' + numeroPagina);
        window.scrollTo(0, 0);
      });
    }

  }
