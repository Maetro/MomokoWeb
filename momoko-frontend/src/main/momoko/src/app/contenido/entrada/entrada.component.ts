import { Component, OnInit, OnDestroy, Inject } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Entrada } from '../../dtos/entrada';
import { LibroSimple } from '../../dtos/libroSimple';
import { Comentario } from '../../dtos/comentario';
import { EntradaSimple } from '../../dtos/entradaSimple';
import { EntradaService } from '../../services/entrada.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ObtenerEntradaResponse } from '../../dtos/response/obtenerEntradaResponse';
import { Meta, Title } from '@angular/platform-browser';
import { UtilService } from '../../services/util/util.service';

@Component({
  selector: 'app-entrada',
  templateUrl: './entrada.component.html',
  styleUrls: ['./entrada.component.scss']
})
export class EntradaComponent implements OnInit, OnDestroy {
  private log = environment.log;

  private url: string;

  entrada: Entrada;

  suscriptor: any;

  librosParecidos: LibroSimple[];

  comentarios: Comentario[];

  entradaAnteriorYSiguiente: EntradaSimple[];

  cuatroPostPequenosConImagen: EntradaSimple[];

  tipoEntrada: number;

  haySagas: boolean;

  hayLibros: boolean;

  constructor(
    private entradaService: EntradaService,
    private route: ActivatedRoute,
    private router: Router,
    private util: UtilService,
    private metaService: Meta,
    private titleService: Title
  ) {}

  ngOnInit() {
    if (this.log) {
      console.log('Creando pagina de la entrada');
    }
    this.suscriptor = this.route.params.subscribe(params => {
      this.url = params['url']; // (+) converts string 'id' to a number
      if (this.log) {
        console.log(this.url);
      }
      this.route.data.subscribe(
        (data: { obtenerEntradaResponse: ObtenerEntradaResponse }) => {
          if (data.obtenerEntradaResponse.entrada){
            this.entrada = data.obtenerEntradaResponse.entrada;
            this.librosParecidos =
              data.obtenerEntradaResponse.cincoLibrosParecidos;
            this.comentarios = data.obtenerEntradaResponse.comentarios;
            this.tipoEntrada = data.obtenerEntradaResponse.entrada.tipoEntrada;
            this.hayLibros =
              data.obtenerEntradaResponse.entrada.librosEntrada.length > 0;
            this.haySagas =
              data.obtenerEntradaResponse.entrada.sagasEntrada.length > 0;
            this.entradaAnteriorYSiguiente =
              data.obtenerEntradaResponse.obtenerEntradaAnteriorYSiguiente;
            this.cuatroPostPequenosConImagen =
              data.obtenerEntradaResponse.cuatroPostPequenosConImagen;
  
            this.util.removeAllTags(this.metaService);
  
            this.metaService.addTag({
              name: 'og:url',
              content: 'https://momoko.es/' + this.url
            });
            this.metaService.addTag({ name: 'og:locale', content: 'es_ES' });
            this.metaService.addTag({
              name: 'fb:app_id',
              content: '1932678757049258'
            });
          } else{
            const metatituloPagina =
              'Momoko 404 - Contenido no encontrado';
            this.titleService.setTitle(metatituloPagina);
          }
          
        }
      );
    });
  }

  eliminarTag(tag: string) {
    this.metaService.removeTag(tag);
  }

  isActive(instruction: any[]): boolean {
    // Set the second parameter to true if you want to require an exact match.
    return this.router.isActive(this.router.createUrlTree(instruction), false);
  }

  ngOnDestroy() {
    this.suscriptor.unsubscribe();
  }
}
