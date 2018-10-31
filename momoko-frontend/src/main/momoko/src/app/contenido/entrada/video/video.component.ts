import { Component, OnInit, AfterViewInit, Input, Inject, PLATFORM_ID, ViewChild, TemplateRef } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Entrada } from '../../../dtos/entrada';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { LibroSimple } from '../../../dtos/libroSimple';
import { SafeUrl, DomSanitizer, Title, Meta } from '@angular/platform-browser';
import { isPlatformBrowser } from '@angular/common';
import { Comentario } from '../../../dtos/comentario';

declare var $: any;

@Component({
  selector: 'app-video',
  templateUrl: './video.component.html',
  styleUrls: ['./video.component.css']
})
export class VideoComponent implements OnInit, AfterViewInit {

  private log = environment.log;

  @Input() entrada: Entrada;

  @Input() entradaAnteriorYSiguiente: EntradaSimple[];

  @Input() cuatroPostPequenosConImagen: EntradaSimple[];

  @Input() librosParecidos: LibroSimple[];

  @Input() comentarios: Comentario[];

  @ViewChild('book-template-angular')
  bookTemplate: TemplateRef<any>;

  tituloSeccionLibros = 'Otros libros parecidos';

  backgroundImage = '/assets/style/images/art/parallax2.jpg';

  codigoVideo: string;

  safeURL: SafeUrl;

  content: string;

  htmlContent: string[];

  bookTemplates: string[];

  constructor(private domSanitizationService: DomSanitizer, private titleService: Title, private metaService: Meta, @Inject(PLATFORM_ID) private platformId: Object) { }

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
    this.metaService.addTag({ name: 'og:type', content: 'video.other' });
    this.metaService.addTag({ name: 'og:title', content: this.entrada.tituloEntrada });
    this.metaService.addTag({ name: 'og:description', content: this.entrada.fraseDescriptiva });
    this.metaService.addTag({ name: 'og:image', content: this.entrada.imagenDestacada });
    this.metaService.addTag({ name: 'og:video', content: url });
  }

  ngAfterViewInit(): void {
    if (isPlatformBrowser(this.platformId)) {
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
    const columna = document.getElementById('bookTemplate1')
    const width = columna.offsetWidth;
    const style = window.getComputedStyle(columna);
    // tslint:disable-next-line:radix
    const margin = parseInt(style.paddingLeft) + parseInt(style.paddingRight);
    let anchura = width - margin;
    console.log('anchura');
    
    for (let cont = 1; cont <= this.bookTemplates.length; cont++){
      let replaceCode = $($("app-book-template").get(cont-1)).html();
      $($("app-book-template").get(cont-1)).html("");
      $(".bookTemplate"+ cont).html(replaceCode);
    }
  }

  crearCollage() {
    $('.collage').attr('id', 'collage-large');
    this.collage();
    $('.collage .collage-image-wrapper').css('opacity', 0);
    $('.overlay a').prepend('<span class="over"><span></span></span>');
  }

  collage() {
    $('.collage')
    .removeWhitespace()
    .collagePlus({
      fadeSpeed: 5000,
      targetHeight: 400,
      effect: 'effect-2',
      direction: 'vertical',
      allowPartialLastRow: true
    });
  };

  youtubeURL() {

  }
}
