import { Component, OnDestroy, OnInit } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from '../../../../environments/environment';
import { Genero } from '../../../dtos/genero';
import { LibroSimple } from '../../../dtos/libroSimple';
import { ObtenerPaginaGeneroResponse } from '../../../dtos/response/obtenerPaginaGeneroResponse';
import { ClasificadorService } from '../../../services/clasificador.service';
import { UtilService } from '../../../services/util/util.service';
import { EntradaSimple } from './../../../dtos/entradaSimple';



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

  numeroEntradas: number;

  numeroEntradasPagina = 9;

  numeroPaginas: number;

  numbers

  isMobile:boolean; 

  constructor(private clasificadorService: ClasificadorService, private route: ActivatedRoute, private router: Router,
    private titleService: Title, private metaService: Meta, private util: UtilService) { }

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
        this.util.removeAllTags(this.metaService);
        this.librosGenero = data.paginaGeneroResponse.nueveLibrosGenero;
        this.entradasPopulares = data.paginaGeneroResponse.tresUltimasEntradasConLibro;
        const metatituloPagina = 'Aquí encontrarás críticas, reseñas, opiniones y análisis de los libros del género ' + this.genero.nombre +
          ' en momoko';
          const tag = {
            name: 'description', content: 'Últimas fichas de los libros del género ' + this.genero.nombre +
              'desde donde podras acceder a sus noticias y análisis'
          };
          this.metaService.addTag(tag, false);
        this.titleService.setTitle(metatituloPagina);
        this.numeroEntradas = data.paginaGeneroResponse.numeroLibros;
        this.numeroPaginas = Math.ceil(this.numeroEntradas / this.numeroEntradasPagina);
        this.numbers = Array(this.numeroPaginas).fill(0).map((x, i) => i + 1);
      });

      // const columna = document.getElementById('mirarAnchura0');
      // const width = columna.offsetWidth;
      // const style = window.getComputedStyle(columna);
      // // tslint:disable-next-line:radix
      // const margin = parseInt(style.paddingLeft) + parseInt(style.paddingRight);
      // this.anchura = width - margin;
      // In a real app: dispatch action to load the details here.
    });

    if (window.screen.width < 768){
      this.isMobile = true;
    }
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
