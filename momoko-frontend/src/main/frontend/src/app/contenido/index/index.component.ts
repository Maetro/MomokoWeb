import { Fila3entradasfondonegroComponent } from './fila3entradasfondonegro/fila3entradasfondonegro.component';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { IndexDataService } from '../../services/index-data.service';
import { EntradaSimple } from '../../dtos/entradaSimple';
import { DatePipe } from '@angular/common';
import { EntradaItem } from './entrada-portada/entrada-item';
import { EntradaPortadaNormalImplComponent } from './entrada-portada/entrada-portada-normal.component';
import { AnadirEntradaComponent } from './entrada-portada/anadir-entrada.component';
import { AnadirEntrada2Component } from './entrada-portada/anadir-entrada2.component';
import { LibroSimple } from '../../dtos/libroSimple';
import { LibrosHorizontalComponent } from './libros-horizontal/libros-horizontal.component';
import { YoutubeService } from '../../services/youtube.service';
import { VideoYoutube } from '../../services/youtube-api/youtube';
import { LibroEntradaSimple } from '../../dtos/simples/libroEntradaSimple';
import { ActivatedRoute, Router } from '@angular/router';
import { ObtenerIndexDataResponse } from '../../dtos/response/obtenerIndexDataResponse';
import { EntradaPortadaVideoComponent } from './entrada-portada/entrada-portada-video/entrada-portada-video.component';
import { environment } from 'environments/environment';

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

  portada = 'http://elmiedodelosesclavos.com/wp-content/uploads/2017/01/El-miedo-de-los-esclavos-2-345x520.jpg';

  tituloUltimosAnalisis = 'Últimas fichas añadidas';

  @ViewChild(AnadirEntradaComponent) anadirEntradaComponent: AnadirEntradaComponent;
  @ViewChild(AnadirEntrada2Component) anadirEntrada2Component: AnadirEntrada2Component;
  @ViewChild(Fila3entradasfondonegroComponent) fila3entradasfondonegroComponent: Fila3entradasfondonegroComponent;
  @ViewChild(LibrosHorizontalComponent) librosHorizontalComponent: LibrosHorizontalComponent;

  constructor(private route: ActivatedRoute, private router: Router, private youtubeService: YoutubeService) { }

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
        e = new EntradaItem(EntradaPortadaNormalImplComponent, entradaSimple);
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
