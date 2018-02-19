import { LinkService } from './../../../services/link.service';
import { Component, OnInit, AfterViewInit, Input, Inject, PLATFORM_ID } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Entrada } from '../../../dtos/entrada';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { LibroSimple } from '../../../dtos/libroSimple';
import { Title, Meta } from '@angular/platform-browser';
import { isPlatformBrowser } from '@angular/common';
import { Comentario } from '../../../dtos/comentario';

declare var $: any;

@Component({
  selector: 'app-miscelaneo',
  templateUrl: './miscelaneo.component.html',
  styleUrls: ['./miscelaneo.component.css']
})
export class MiscelaneoComponent implements OnInit, AfterViewInit {

  private log = environment.log;

  @Input() entrada: Entrada;

  @Input() entradaAnteriorYSiguiente: EntradaSimple[];

  @Input() cuatroPostPequenosConImagen: EntradaSimple[];

  @Input() librosParecidos: LibroSimple[];

  @Input() comentarios: Comentario[];

  backgroundImage = '/assets/style/images/art/parallax2.jpg';

  tituloSeccionLibros = 'Otros libros parecidos';

  constructor(private titleService: Title, private metaService: Meta,
     @Inject(PLATFORM_ID) private platformId: Object, private linkService: LinkService) { }

  ngOnInit() {
    const metatituloPagina = this.entrada.tituloEntrada;
    this.titleService.setTitle(metatituloPagina);
    // Changing meta with name="description"
    const tag = { name: 'description', content: this.entrada.fraseDescriptiva };
    const attributeSelector = 'name="description"';
    this.metaService.removeTag(attributeSelector);
    this.metaService.addTag(tag, false);
    this.metaService.addTag({ name: 'og:type', content: 'article' });
    this.metaService.addTag({ name: 'og:title', content: this.entrada.tituloEntrada });
    this.metaService.addTag({ name: 'og:description', content: this.entrada.fraseDescriptiva });
    this.metaService.addTag({ name: 'og:image', content: this.entrada.imagenDestacada });
    if (this.entrada.librosEntrada != null && this.entrada.librosEntrada.length > 0){
      this.linkService.addTag( { rel: 'canonical', href: 'http://momoko.es/libro/' + 
      this.entrada.librosEntrada[0].urlLibro +'/miscelaneo/' +  this.entrada.urlEntrada} );
    } else {
      this.linkService.addTag( { rel: 'canonical', href: 'http://momoko.es/' +  this.entrada.urlEntrada} );
    }
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

}