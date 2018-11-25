import { UtilService } from '../../services/util/util.service';
import {
  Component,
  OnInit,
  AfterViewInit,
  ViewChild,
  Inject,
  PLATFORM_ID,
  ComponentFactoryResolver
} from '@angular/core';
import { environment } from '../../../environments/environment';
import { EntradaSimple } from '../../dtos/entradaSimple';
import { ActivatedRoute, Router } from '@angular/router';
import {  IndexDataResponse} from '../../dtos/response/indexDataResponse';
import { LibroSimple } from '../../dtos/libroSimple';
import { LibroEntradaSimple } from '../../dtos/simples/libroEntradaSimple';
import { VideoYoutube } from '../../services/youtube-api/youtube';
import { Fila3entradasfondonegroComponent } from './fila3entradasfondonegro/fila3entradasfondonegro.component';
import { YoutubeService } from '../../services/youtube.service';
import { isPlatformBrowser, isPlatformServer } from '@angular/common';
import { Meta, Title } from '@angular/platform-browser';
import { LinkService } from '../../services/link.service';
import { CustomBlockIndexDirective } from './custom-block-index/custom-block-index.directive';
import { IndexDataService } from './services/index-data.service';
import { CustomBlockComponent } from '../comunes/custom-block/custom-block.component';

declare var $: any;

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit, AfterViewInit {
  private log = environment.log;

  lastOpinions: EntradaSimple[] = [];
  lastNews: EntradaSimple[] = [];
  lastMiscellaneous: EntradaSimple[] = [];
  librosMasLeidosMes: LibroSimple[];
  ultimoComicAnalizado: LibroEntradaSimple;
  librosUltimosAnalisis: LibroSimple[];
  ultimos3Videos: VideoYoutube[];

  @ViewChild(CustomBlockIndexDirective) customBlockHost: CustomBlockIndexDirective;

  tituloSeccionLibros = 'Lo más leído este mes...';

  tituloUltimosAnalisis = 'Últimas fichas añadidas';

  @ViewChild(Fila3entradasfondonegroComponent)
  fila3entradasfondonegroComponent: Fila3entradasfondonegroComponent;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private youtubeService: YoutubeService,
    private metaService: Meta,
    private titleService: Title,
    private linkService: LinkService,
    private indexDataService: IndexDataService,
    private util: UtilService,
    private componentFactoryResolver: ComponentFactoryResolver,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit() {
    this.route.data.subscribe(
      (data: { indexDataResponse: IndexDataResponse }) => {
        if (this.log) {
          console.log('Init index');
        }
        this.titleService.setTitle(
          'Momoko - blog de literatura, análisis y noticias de libros'
        );
        this.util.removeAllTags(this.metaService);
        this.metaService.addTag({
          name: 'description',
          content:
            'Momoko es tu blog de referencia de noticias literarias, análisis y reseñas de cómics, libros, clásicos, novelas gráficas y mucho más.'
        });
        this.metaService.addTag({
          name: 'og:url',
          content: 'https://momoko.es'
        });
        this.metaService.addTag({ name: 'og:locale', content: 'es_ES' });
        this.metaService.addTag({
          name: 'fb:app_id',
          content: '1932678757049258'
        });
        this.metaService.addTag({ name: 'og:type', content: 'article' });
        this.metaService.addTag({
          name: 'og:title',
          content: 'Momoko - blog de literatura, análisis y noticias de libros'
        });
        this.metaService.addTag({
          name: 'og:description',
          content:
            'Momoko es tu blog de referencia de noticias literarias, análisis y reseñas de cómics, libros, clásicos, novelas gráficas y mucho más.'
        });
        this.metaService.addTag({
          name: 'og:image',
          content: 'https://momoko.es/assets/style/images/logo.png'
        });
        this.linkService.addTag({
          rel: 'canonical',
          href: 'https://momoko.es'
        });
        this.lastOpinions = data.indexDataResponse.lastOpinions;
        this.lastNews = data.indexDataResponse.lastNews;
        this.lastMiscellaneous = data.indexDataResponse.lastMiscellaneous;
        this.librosMasLeidosMes = data.indexDataResponse.librosMasVistos;
        this.librosUltimosAnalisis = data.indexDataResponse.ultimosAnalisis;
        this.ultimoComicAnalizado = data.indexDataResponse.ultimoComicAnalizado;
      },
      error => {
        if (this.log) {
          console.log('Error al recuperar los datos generales ', error);
        }
      }
    );

    this.youtubeService.getMomokoFeed(3).subscribe(datos => {
      this.ultimos3Videos = datos.items;
    });
  }

  
  ngAfterViewInit(): void {
    if (this.log) {
      console.log('Ejecutando JQuery');
    }

    if (isPlatformBrowser(this.platformId)) {
      // Client only code.
      $('.swiper-container.image-blog-wide').each(function() {
        $(this).swiper({
          pagination: '.image-blog-wide-wrapper .swiper-pagination',
          nextButton: '.image-blog-wide-wrapper .swiper-button-next',
          prevButton: '.image-blog-wide-wrapper .swiper-button-prev',
          slidesPerView: 3,
          breakpoints: {
            991: {
              slidesPerView: 1
            },
            1681: {
              slidesPerView: 2
            }
          },
          centeredSlides: false,
          paginationClickable: true,
          spaceBetween: 10,
          grabCursor: true
        });
        const $swipers = $(this);
      });
    }
    if (isPlatformServer(this.platformId)) {
      // Server only code.
    }
    this.loadComponent();
  }

  loadComponent() {
    let customBlockItem = this.indexDataService.getCustomBlockIndex().subscribe(customBlock =>{
      let componentFactory = this.componentFactoryResolver.resolveComponentFactory(CustomBlockComponent);

      let viewContainerRef = this.customBlockHost.viewContainerRef;
      viewContainerRef.clear();
  
      let componentRef = viewContainerRef.createComponent(componentFactory);
      (<CustomBlockComponent>componentRef.instance).customBlock = customBlock;
    });

  
  }

  obtenerUrlEntradaSimple(entrada: EntradaSimple): string {
    return this.util.obtenerUrlEntradaSimple(entrada);
  }
}
