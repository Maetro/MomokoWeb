import { Component, OnDestroy, OnInit } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from '../../../../environments/environment';
import { Editorial } from '../../../dtos/editorial';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { LibroSimple } from '../../../dtos/libroSimple';
import { ObtenerPaginaEditorialResponse } from '../../../dtos/response/obtenerPaginaEditorialResponse';
import { ClasificadorService } from '../../../services/clasificador.service';
import { UtilService } from '../../../services/util/util.service';

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
    private titleService: Title, private metaService: Meta, private util: UtilService) { }

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

        const metatituloPagina = 'Editorial: ' + this.editorial.nombreEditorial + ': Últimos libros en momoko de la editorial: ' +
           this.editorial.nombreEditorial;
        this.titleService.setTitle(metatituloPagina);
        this.numeroLibros = data.editorial.numeroLibros;
        this.numeroPaginas = Math.ceil(this.numeroLibros / this.numeroLibrosPagina);
        this.numbers = Array(this.numeroPaginas).fill(0).map((x, i) => i + 1);

        this.util.removeAllTags(this.metaService);


        this.titleService.setTitle('Momoko - Editorial: ' + this.editorial.nombreEditorial);
        const tag = {
          name: 'description', content: metatituloPagina
        };
        this.metaService.addTag(tag, false);
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
