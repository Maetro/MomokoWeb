import { Component, OnInit, AfterViewInit, ViewChild, Inject, PLATFORM_ID } from '@angular/core';
import { environment } from '../../../environments/environment';
import { EntradaSimple } from '../../dtos/entradaSimple';
import { ActivatedRoute, Router } from '@angular/router';
import { ObtenerIndexDataResponse } from '../../dtos/response/obtenerIndexDataResponse';
import { LibroSimple } from '../../dtos/libroSimple';
import { LibroEntradaSimple } from '../../dtos/simples/libroEntradaSimple';
import { EntradaItem } from './entrada-portada/entrada-item';
import { VideoYoutube } from '../../services/youtube-api/youtube';
import { EntradaPortadaVideoComponent } from './entrada-portada/entrada-portada-video/entrada-portada-video.component';
import { AnadirEntradaComponent } from './entrada-portada/anadir-entrada/anadir-entrada.component';
import { AnadirEntrada2Component } from './entrada-portada/anadir-entrada2/anadir-entrada2.component';
import { EntradaPortadaNormalComponent } from './entrada-portada/entrada-portada-normal/entrada-portada-normal.component';
import { Fila3entradasfondonegroComponent } from './fila3entradasfondonegro/fila3entradasfondonegro.component';
import { YoutubeService } from '../../services/youtube.service';
import { isPlatformBrowser, isPlatformServer } from '@angular/common';
import { Meta } from '@angular/platform-browser';

declare var $: any;

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit, AfterViewInit {

  private log = environment.log;

  ultimasEntradas: EntradaSimple[] = [];
  ultimas3Entradas: EntradaSimple[] = [];
  entradas1a5: EntradaSimple[] = [];
  entradas8a10: EntradaSimple[] = [];
  entradasPortada: EntradaItem[];
  librosMasLeidosMes: LibroSimple[];
  ultimoComicAnalizado: LibroEntradaSimple;
  librosUltimosAnalisis: LibroSimple[];
  ultimos3Videos: VideoYoutube[];

  tituloSeccionLibros = 'Lo más leído este mes...';

  tituloUltimosAnalisis = 'Últimas fichas añadidas';

  @ViewChild(AnadirEntradaComponent) anadirEntradaComponent: AnadirEntradaComponent;
  @ViewChild(AnadirEntrada2Component) anadirEntrada2Component: AnadirEntrada2Component;
  @ViewChild(Fila3entradasfondonegroComponent) fila3entradasfondonegroComponent: Fila3entradasfondonegroComponent;

  constructor(
    private route: ActivatedRoute, 
    private router: Router,
    private youtubeService: YoutubeService,
    private metaService: Meta, 
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }

  ngOnInit() {
    this.route.data.subscribe((data: { obtenerIndexDataResponse: ObtenerIndexDataResponse }) => {
      if (this.log) {
        console.log('Init index');
      }
      this.ultimasEntradas = data.obtenerIndexDataResponse.ultimasEntradas;
      this.obtenerEntradasPortada(data.obtenerIndexDataResponse.ultimasEntradas);
      this.librosMasLeidosMes = data.obtenerIndexDataResponse.librosMasVistos;
      this.librosUltimosAnalisis = data.obtenerIndexDataResponse.ultimosAnalisis;
      this.ultimoComicAnalizado = data.obtenerIndexDataResponse.ultimoComicAnalizado;
      this.metaService.addTag({ name: 'og:url', content: 'http://momoko.es'});
      this.metaService.addTag({ name: 'og:locale', content: 'es_ES' });
      this.metaService.addTag({ name: 'fb:app_id', content: '1932678757049258' });
      this.metaService.addTag({ name: 'og:type', content: 'article' });
      this.metaService.addTag({ name: 'og:title', content: 'Momoko - blog de literatura, análisis y noticias de libros'});
      this.metaService.addTag({ name: 'og:description', content: 'Momoko es tu blog de referencia de noticias literarias, análisis y reseñas de cómics, libros, clásicos, novelas gráficas y mucho más.'});
      this.metaService.addTag({ name: 'og:image', content: 'http://momoko.es/assets/style/images/logo.png' });
    }, error => {
      if (this.log) {
        console.log('Error al recuperar los datos generales ', error);
      }
    });


    this.youtubeService.getMomokoFeed(3).subscribe(datos => {
      this.ultimos3Videos = datos.items;
    })

  }

  ngAfterViewInit(): void {
    
    if (this.log) {
      console.log('Ejecutando JQuery');
    }

    if (isPlatformBrowser(this.platformId)) {
      // Client only code.
      $('.swiper-container.image-blog-wide').each(function () {
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

    

  }

  obtenerEntradasPortada(entradas: EntradaSimple[]) {
    this.entradasPortada = [];
    let entradasBD: EntradaItem[];
    entradasBD = [];
    if (this.log) {
      console.log('Obtener entradas portada');
    }
    for (let i = 5; i < 7; i++) {
      const entradaSimple = entradas[i];
      let e: EntradaItem;
      if (entradaSimple.tipoEntrada === 'Vídeo') {
        e = new EntradaItem(EntradaPortadaVideoComponent, entradaSimple);
      } else {
        e = new EntradaItem(EntradaPortadaNormalComponent, entradaSimple);
      }
      entradasBD.push(e);
    }

    this.entradasPortada = entradasBD;
    this.anadirEntradaComponent.loadComponent(this.entradasPortada[0]);
    this.anadirEntrada2Component.loadComponent(this.entradasPortada[1]);

    for (let pos = 0; pos < 5; pos++) {
      this.entradas1a5.push(this.ultimasEntradas[pos]);
    }

    for (let pos = this.ultimasEntradas.length - 3; pos < this.ultimasEntradas.length; pos++) {
      this.ultimas3Entradas.push(this.ultimasEntradas[pos]);
    }
    this.fila3entradasfondonegroComponent.loadEntradas(this.ultimas3Entradas);
  }


}
