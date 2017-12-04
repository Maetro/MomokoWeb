import { Fila3entradasfondonegroComponent } from './fila3entradasfondonegro/fila3entradasfondonegro.component';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { IndexDataService } from 'app/services/index-data.service';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { DatePipe } from '@angular/common';
import { EntradaItem } from 'app/contenido/index/entrada-portada/entrada-item';
import { EntradaPortadaNormalImplComponent } from 'app/contenido/index/entrada-portada/entrada-portada-normal.component';
import { AnadirEntradaComponent } from 'app/contenido/index/entrada-portada/anadir-entrada.component';
import { AnadirEntrada2Component } from 'app/contenido/index/entrada-portada/anadir-entrada2.component';
import { LibroSimple } from 'app/dtos/libroSimple';
import { LibrosHorizontalComponent } from 'app/contenido/index/libros-horizontal/libros-horizontal.component';
import { YoutubeService } from 'app/services/youtube.service';
import { VideoYoutube } from 'app/services/youtube-api/youtube';
import { LibroEntradaSimple } from 'app/dtos/simples/libroEntradaSimple';
import { ActivatedRoute, Router } from '@angular/router';
import { ObtenerIndexDataResponse } from 'app/dtos/response/obtenerIndexDataResponse';

declare var $: any;
declare var Instafeed: any;

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit, AfterViewInit {

  ultimasEntradas: EntradaSimple[] = [];
  ultimas3Entradas: EntradaSimple[] = [];
  entradas1a5: EntradaSimple[] = [];
  entradas8a10: EntradaSimple[] = [];
  entradasPortada: EntradaItem[];
  librosMasLeidosMes: LibroSimple[];
  ultimoComicAnalizado: LibroEntradaSimple;
  librosUltimosAnalisis: LibroSimple[];
  ultimos3Videos: VideoYoutube[];

  portada = 'http://elmiedodelosesclavos.com/wp-content/uploads/2017/01/El-miedo-de-los-esclavos-2-345x520.jpg';

  tituloUltimosAnalisis = 'Últimos análisis';

  @ViewChild(AnadirEntradaComponent) anadirEntradaComponent: AnadirEntradaComponent;
  @ViewChild(AnadirEntrada2Component) anadirEntrada2Component: AnadirEntrada2Component;
  @ViewChild(Fila3entradasfondonegroComponent) fila3entradasfondonegroComponent: Fila3entradasfondonegroComponent;
  @ViewChild(LibrosHorizontalComponent) librosHorizontalComponent: LibrosHorizontalComponent;

  constructor(private route: ActivatedRoute, private router: Router, private youtubeService: YoutubeService) { }

  ngOnInit() {
    this.route.data.subscribe((data: { obtenerIndexDataResponse: ObtenerIndexDataResponse }) => {
      console.log('Init index');
      this.ultimasEntradas = data.obtenerIndexDataResponse.ultimasEntradas;
      this.obtenerEntradasPortada(data.obtenerIndexDataResponse.ultimasEntradas);
      this.librosMasLeidosMes = data.obtenerIndexDataResponse.librosMasVistos;
      this.librosUltimosAnalisis = data.obtenerIndexDataResponse.ultimosAnalisis;
      this.ultimoComicAnalizado = data.obtenerIndexDataResponse.ultimoComicAnalizado;
    }, error => {
      console.log('Error al recuperar los datos generales ', error);
    });

    this.youtubeService.getMomokoFeed(3).subscribe(datos => {
      this.ultimos3Videos = datos.items;
    })

  }

  ngAfterViewInit(): void {
    console.log('Ejecutando JQuery');
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
    const instagramFeed = new Instafeed({
      target: 'instafeed-widget',
      get: 'user',
      limit: 12,
      userId: 3260305017,
      accessToken: '3260305017.b4c416e.5f99e592a4bd49afa1b256faa597146c',
      resolution: 'low_resolution',
      clientId: 'b4c416e8ab3f424d915b5601f5d3dd88',
      // tslint:disable-next-line:max-line-length
      template: '<div class="item col-xs-4 col-sm-6 col-md-4"><figure class="overlay small"><a href="{{link}}" target="_blank"><img src="{{image}}" /></a></figure></div>',
      after: function () {
        $('#instafeed-widget figure.overlay a').prepend('<span class="over"><span></span></span>');
      }
    });
    $('#instafeed-widget').each(function () {
      instagramFeed.run();
    });
  }

  obtenerEntradasPortada(entradas: EntradaSimple[]) {
    this.entradasPortada = [];
    let entradasBD: EntradaItem[];
    entradasBD = [];
    for (let i = 5; i < 7; i++) {
      const entradaSimple = entradas[i];
      let e: EntradaItem;
      // if (entradaSimple.categoria === 'MISCELANEOS') {
      e = new EntradaItem(EntradaPortadaNormalImplComponent, entradaSimple);
      // }
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
