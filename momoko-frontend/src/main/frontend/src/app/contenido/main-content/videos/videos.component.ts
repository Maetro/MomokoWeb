import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import { Entrada } from 'app/dtos/entrada';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { DomSanitizer, SafeUrl} from '@angular/platform-browser';

declare var $: any;

@Component({
  selector: 'app-videos',
  templateUrl: './videos.component.html',
  styleUrls: ['./videos.component.css']
})
export class VideosComponent implements OnInit, AfterViewInit {

  @Input() entrada: Entrada;

  @Input() entradaAnteriorYSiguiente: EntradaSimple[];

  @Input() cuatroPostPequenosConImagen: EntradaSimple[];

  backgroundImage = '/assets/style/images/art/parallax2.jpg';

  codigoVideo: string;

  safeURL: SafeUrl;

  constructor(private domSanitizationService: DomSanitizer ) { }

  ngOnInit() {
    console.log('Cargando video');
    const partes = this.entrada.urlVideo.split('/');
    const url = 'https://www.youtube.com/embed/' + partes[partes.length - 1];
    this.safeURL = this.domSanitizationService.bypassSecurityTrustResourceUrl(url);
  }

  ngAfterViewInit(): void {
    console.log('Ejecutando JQuery');
    $('.light-gallery').lightGallery({
      thumbnail: false,
      selector: '.lgitem',
      animateThumb: true,
      showThumbByDefault: false,
      download: false,
      autoplayControls: false,
      zoom: false,
      fullScreen: false,
      thumbWidth: 100,
      thumbContHeight: 80,
      hash: false,
      videoMaxWidth: '1000px'
    });
    setTimeout(() => this.crearCollage(), 2000);

  }

  crearCollage() {
    $('.collage').attr('id', 'collage-large');
    this.collage();
    $('.collage .collage-image-wrapper').css('opacity', 0);
    $('.overlay a').prepend('<span class="over"><span></span></span>');
  }

  collage() {
    $('#collage-large').removeWhitespace().collagePlus({
      'fadeSpeed': 5000,
      'targetHeight': 400,
      'effect': 'effect-2',
      'direction': 'vertical',
      'allowPartialLastRow': true
    });
    $('#collage-medium').removeWhitespace().collagePlus({
      'fadeSpeed': 5000,
      'targetHeight': 300,
      'effect': 'effect-2',
      'direction': 'vertical',
      'allowPartialLastRow': true
    });
  };

  youtubeURL() {

  }
}
