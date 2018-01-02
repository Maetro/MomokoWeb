import { EntradaSimple } from './../../../dtos/entradaSimple';
import { LibroSimple } from 'app/dtos/libroSimple';

import { Component, OnInit, OnDestroy } from '@angular/core';
import { Genero } from 'app/dtos/genero';
import { ActivatedRoute, Router } from '@angular/router';
import { ObtenerPaginaGeneroResponse } from 'app/dtos/response/obtenerPaginaGeneroResponse';
import { ClasificadorService } from 'app/services/clasificador.service';
import { environment } from 'environments/environment';
import { Title } from '@angular/platform-browser';

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

  tituloSeccionLibros = 'Otros libros parecidos';

  constructor(private clasificadorService: ClasificadorService, private route: ActivatedRoute, private router: Router,
    private titleService: Title) { }

  ngOnInit() {
    if (this.log) {
      console.log('Creando pagina del genero');
    }
    this.suscriptor = this.route.params.subscribe(params => {
      this.url = params['url']; // (+) converts string 'id' to a number
      if (this.log) {
        console.log(this.url);
      }
      this.route.data.subscribe((data: { paginaGeneroResponse: ObtenerPaginaGeneroResponse }) => {
        this.genero = data.paginaGeneroResponse.genero;
        this.librosGenero = data.paginaGeneroResponse.nueveLibrosGenero;
        this.entradasPopulares = data.paginaGeneroResponse.tresUltimasEntradasConLibro;
        const metatituloPagina = 'Aquí encontrarás críticas, reseñas, opiniones y análisis de los libros del género ' + this.genero.nombre +
          ' en momoko';
        this.titleService.setTitle(metatituloPagina);
      });

      // const columna = document.getElementById('mirarAnchura0');
      // const width = columna.offsetWidth;
      // const style = window.getComputedStyle(columna);
      // // tslint:disable-next-line:radix
      // const margin = parseInt(style.paddingLeft) + parseInt(style.paddingRight);
      // this.anchura = width - margin;
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

  mirarAnchura(): number {
    return 236;
  }

}
