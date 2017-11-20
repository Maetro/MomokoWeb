import { EntradaSimple } from './../../../dtos/entradaSimple';
import { LibroSimple } from 'app/dtos/libroSimple';

import { Component, OnInit, OnDestroy } from '@angular/core';
import { Genero } from 'app/dtos/genero';
import { ActivatedRoute, Router } from '@angular/router';
import { ObtenerPaginaGeneroResponse } from 'app/dtos/response/obtenerPaginaGeneroResponse';
import { ClasificadorService } from 'app/services/clasificador.service';

@Component({
  selector: 'app-lista-genero',
  templateUrl: './lista-genero.component.html',
  styleUrls: ['./lista-genero.component.css']
})
export class ListaGeneroComponent implements OnInit, OnDestroy {

  private url: string;

  suscriptor: any;

  librosGenero: LibroSimple[];

  entradasPopulares: EntradaSimple[];

  genero: Genero;

  constructor(private clasificadorService: ClasificadorService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    console.log('Creando pagina del genero')
    this.suscriptor = this.route.params.subscribe(params => {
      this.url = params['url']; // (+) converts string 'id' to a number
      console.log(this.url);

      this.route.data.subscribe((data: { paginaGeneroResponse: ObtenerPaginaGeneroResponse }) => {
        this.genero = data.paginaGeneroResponse.genero;
        this.librosGenero = data.paginaGeneroResponse.nueveLibrosGenero;
        this.entradasPopulares = data.paginaGeneroResponse.tresUltimasEntradasConLibro;
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

}
