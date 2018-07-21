import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { SagaService } from '../../../services/saga.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Title, Meta } from '@angular/platform-browser';
import { FichaSaga } from '../../../dtos/fichaSaga';
import { Saga } from '../../../dtos/saga';
import { Libro } from '../../../dtos/libro';
import { environment } from '../../../../environments/environment';
import { EntradaSimple } from '../../../dtos/entradaSimple';

@Component({
  selector: 'app-ficha-saga',
  templateUrl: './ficha-saga.component.html',
  styleUrls: ['./ficha-saga.component.css']
})
export class FichaSagaComponent implements OnInit {
  private log = environment.log;

  saga: Saga;
  librosSaga: Libro[];
  entradas: EntradaSimple[];
  entradasLibros: EntradaSimple[];
  mapaOrdinales = [];
  urlAnalisis: string;

  constructor(
    private sagaService: SagaService,
    private route: ActivatedRoute,
    private router: Router,
    private titleService: Title,
    private metaService: Meta,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit() {
    if (this.log) {
      console.log('Creando pagina de la saga');
    }
    this.mapaOrdinales.push('Primer');
    this.mapaOrdinales.push('Segundo');
    this.mapaOrdinales.push('Tercer');
    this.mapaOrdinales.push('Cuarto');
    this.mapaOrdinales.push('Quinto');
    this.mapaOrdinales.push('Sexto');
    this.mapaOrdinales.push('Séptimo');
    this.mapaOrdinales.push('Octavo');
    this.mapaOrdinales.push('Noveno');
    this.route.data.subscribe((data: { fichaSaga: FichaSaga }) => {
      this.saga = data.fichaSaga.saga;
      this.librosSaga = data.fichaSaga.librosSaga;
      this.entradas = data.fichaSaga.tresUltimasEntradas;
      this.entradasLibros = data.fichaSaga.tresUltimasEntradasLibros;
      this.librosSaga.sort(function(a, b) {
        if (a.ordenSaga < b.ordenSaga) {
          return -1;
        } else if (a.ordenSaga > b.ordenSaga) {
          return 1;
        } else {
          return 0;
        }
      });
      const titulo = 'Ficha de saga ' + this.saga.nombreSaga;
      console.log(this.librosSaga[0].generos);
      const metatituloPagina =
        'Encuentra aquí toda la información sobre ' + this.saga.nombreSaga;
      this.titleService.setTitle(titulo);
      // Changing meta with name="description"
      const tag = { name: 'description', content: metatituloPagina };
      const attributeSelector = 'name="description"';
      this.metaService.removeTag(attributeSelector);
      this.metaService.addTag(tag, false);
      this.metaService.removeTag('name="og:url"');
      this.metaService.removeTag('name="og:type"');
      this.metaService.removeTag('name="og:title"');
      this.metaService.removeTag('name="og:description"');
      this.metaService.removeTag('name="og:image"');
      this.metaService.addTag({
        name: 'og:url',
        content: 'https://momoko.es/saga/' + this.saga.urlSaga
      });
      this.metaService.addTag({ name: 'og:locale', content: 'es_ES' });
      this.metaService.addTag({
        name: 'fb:app_id',
        content: '1932678757049258'
      });
      this.metaService.addTag({ name: 'og:type', content: 'book' });
      this.metaService.addTag({
        name: 'og:title',
        content: this.saga.nombreSaga
      });
      this.metaService.addTag({
        name: 'og:description',
        content: this.saga.resumen
      });
      this.metaService.addTag({
        name: 'og:image',
        content: this.saga.imagenSaga
      });
      if (this.saga.entradasSaga) {
        this.saga.entradasSaga.forEach(entrada => {
          if (entrada.tipoEntrada === 2) {
            this.urlAnalisis = '/opiniones/' + entrada.urlEntrada;
          }
        });
      }
    });
  }

  existeAnalisis(libro: Libro) {
    let result = '';
    if (!libro.tieneOpinion) {
      result = 'grayscale';
    }
    return result;
  }
}
