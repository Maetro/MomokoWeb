import { LinkService } from '../../../services/link.service';
import {
  Component,
  OnInit,
  AfterViewInit,
  Input,
  Inject,
  PLATFORM_ID,
  ViewChild,
  TemplateRef
} from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Entrada } from '../../../dtos/entrada';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { LibroSimple } from '../../../dtos/libroSimple';
import { Title, Meta } from '@angular/platform-browser';
import { isPlatformBrowser } from '@angular/common';
import { Comentario } from '../../../dtos/comentario';
import { Saga } from '../../../dtos/saga';

declare var $: any;

@Component({
  selector: 'app-miscelaneo-saga',
  templateUrl: './miscelaneo-saga.component.html',
  styleUrls: ['./miscelaneo-saga.component.css']
})
export class MiscelaneoSagaComponent implements OnInit, AfterViewInit {
  private log = environment.log;

  @Input() entrada: Entrada;

  @Input() entradaAnteriorYSiguiente: EntradaSimple[];

  @Input() cuatroPostPequenosConImagen: EntradaSimple[];

  @Input() librosParecidos: LibroSimple[];

  @Input() saga: Saga;

  @Input() comentarios: Comentario[];

  @ViewChild('book-template-angular')
  bookTemplate: TemplateRef<any>;

  backgroundImage = '/assets/style/images/art/parallax2.jpg';

  tituloSeccionLibros = 'Otros libros parecidos';

  schema;

  content: string;

  htmlContent: string[];

  bookTemplates: string[];

  constructor(
    private titleService: Title,
    private metaService: Meta,
    @Inject(PLATFORM_ID) private platformId: Object,
    private linkService: LinkService
  ) {}

  ngOnInit() {
    const metatituloPagina = this.entrada.tituloEntrada;
    this.titleService.setTitle(metatituloPagina);
    // Changing meta with name="description"
    const tag = { name: 'description', content: this.entrada.fraseDescriptiva };
    const attributeSelector = 'name="description"';
    this.metaService.removeTag(attributeSelector);
    this.metaService.addTag(tag, false);
    this.metaService.addTag({ name: 'og:type', content: 'article' });
    this.metaService.addTag({
      name: 'og:title',
      content: this.entrada.tituloEntrada
    });
    this.metaService.addTag({
      name: 'og:description',
      content: this.entrada.fraseDescriptiva
    });
    this.metaService.addTag({
      name: 'og:image',
      content: this.entrada.imagenDestacada
    });

    this.linkService.removeTag('rel=canonical');
    this.linkService.addTag({
      rel: 'canonical',
      href: 'https://momoko.es/' + this.entrada.urlEntrada
    });

    this.linkService.removeTag('rel=amphtml');
    this.linkService.addTag({
      rel: 'amphtml',
      href: 'https://momoko.es/amp/miscelaneo/' + this.entrada.urlEntrada
    });
    this.schema = JSON.parse(this.entrada.jsonLD);
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
    $('#collage-large')
      .removeWhitespace()
      .collagePlus({
        fadeSpeed: 5000,
        targetHeight: 400,
        effect: 'effect-2',
        direction: 'vertical',
        allowPartialLastRow: true
      });
    $('#collage-medium')
      .removeWhitespace()
      .collagePlus({
        fadeSpeed: 5000,
        targetHeight: 300,
        effect: 'effect-2',
        direction: 'vertical',
        allowPartialLastRow: true
      });
  }
}
