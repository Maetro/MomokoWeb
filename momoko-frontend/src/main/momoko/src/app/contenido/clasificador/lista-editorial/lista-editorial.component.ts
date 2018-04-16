import { Component, OnInit, OnDestroy } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { LibroSimple } from '../../../dtos/libroSimple';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { Editorial } from '../../../dtos/editorial';
import { ClasificadorService } from '../../../services/clasificador.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { ObtenerPaginaEditorialResponse } from '../../../dtos/response/obtenerPaginaEditorialResponse';

@Component({
  selector: 'app-lista-editorial',
  templateUrl: './lista-editorial.component.html',
  styleUrls: ['./lista-editorial.component.css']
})
export class ListaEditorialComponent implements OnInit, OnDestroy {

  private log = environment.log;

  private url: string;

  anchura: number;

  suscriptor: any;

  librosEditorial: LibroSimple[];

  tresUltimasEntradas: EntradaSimple[];

  editorial: Editorial;

  numeroLibros: number;

  numeroLibrosPagina = 9;

  numeroPaginas: number;

  tituloSeccionEntradas = "Últimas entradas de la editorial";

  numbers

  constructor(private clasificadorService: ClasificadorService, private route: ActivatedRoute, private router: Router,
    private titleService: Title) { }

  ngOnInit() {
    if (this.log) {
      console.log('Creando pagina de la editorial');
    }
    this.suscriptor = this.route.params.subscribe(params => {
      this.url = params['url_editorial']; // (+) converts string 'id' to a number
      if (this.log) {
        console.log(this.url);
      }
      this.route.data.subscribe((data: { editorial: ObtenerPaginaEditorialResponse }) => {
        this.editorial = data.editorial.editorial;
        this.librosEditorial = data.editorial.nueveLibrosEditorial;
        this.tresUltimasEntradas = data.editorial.tresUltimasEntradasEditorial;
        const metatituloPagina = 'Aquí encontrarás críticas, reseñas, opiniones y análisis de los libros de la editorial ' + this.editorial.nombreEditorial +
          ' en momoko';
        this.titleService.setTitle(metatituloPagina);
        this.numeroLibros = data.editorial.numeroLibros;
        this.numeroPaginas = Math.ceil(this.numeroLibros / this.numeroLibrosPagina);
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
