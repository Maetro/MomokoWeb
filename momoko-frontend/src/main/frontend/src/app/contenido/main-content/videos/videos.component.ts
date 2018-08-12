import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import { Entrada } from '../../../dtos/entrada';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { DomSanitizer, SafeUrl, Title, Meta } from '@angular/platform-browser';
import { LibroSimple } from '../../../dtos/libroSimple';
import { environment } from 'environments/environment';

declare var $: any;

@Component({
  selector: 'app-videos',
  templateUrl: './videos.component.html',
  styleUrls: ['./videos.component.css']
})
export class VideosComponent implements OnInit, AfterViewInit {

  private log = environment.log;

  @Input() entrada: Entrada;

  @Input() entradaAnteriorYSiguiente: EntradaSimple[];

  @Input() cuatroPostPequenosConImagen: EntradaSimple[];

  @Input() librosParecidos: LibroSimple[];

  tituloSeccionLibros = 'Otros libros parecidos';

  backgroundImage = '/assets/style/images/art/parallax2.jpg';

  codigoVideo: string;

  safeURL: SafeUrl;

  constructor(private domSanitizationService: DomSanitizer, private titleService: Title, private metaService: Meta) { }

  ngOnInit() {
    if (this.log) {
      console.log('Cargando video');
    }
    const partes = this.entrada.urlVideo.split('/');
    const url = 'https://www.youtube.com/embed/' + partes[partes.length - 1];
    this.safeURL = this.domSanitizationService.bypassSecurityTrustResourceUrl(url);
    const metatituloPagina = this.entrada.tituloEntrada;
    this.titleService.setTitle(metatituloPagina);
    // Changing meta with name="description"
    const tag = { name: 'description', content: this.entrada.fraseDescriptiva };
    const attributeSelector = 'name="description"';
    this.metaService.removeTag(attributeSelector);
    this.metaService.addTag(tag, false);
  }

  ngAfterViewInit(): void {
    if (this.log) {
      console.log('Ejecutando JQuery');
    }
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
