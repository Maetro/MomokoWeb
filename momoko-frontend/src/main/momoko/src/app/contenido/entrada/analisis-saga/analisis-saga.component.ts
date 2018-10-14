import {
  Component,
  OnInit,
  AfterViewInit,
  Input,
  PLATFORM_ID,
  Inject,
  ViewChild,
  TemplateRef
} from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Entrada } from '../../../dtos/entrada';
import { LibroSimple } from '../../../dtos/libroSimple';
import { Comentario } from '../../../dtos/comentario';
import { Title, Meta } from '@angular/platform-browser';
import { isPlatformBrowser } from '@angular/common';
import { LinkService } from '../../../services/link.service';

declare var $: any;

@Component({
  selector: 'app-analisis-saga',
  templateUrl: './analisis-saga.component.html',
  styleUrls: ['./analisis-saga.component.css']
})
export class AnalisisSagaComponent implements OnInit, AfterViewInit {
  private log = environment.log;

  @Input() entrada: Entrada;

  @Input() autores: string;

  @Input() librosParecidos: LibroSimple[];

  @Input() comentarios: Comentario[];

  @ViewChild('book-template-angular')
  bookTemplate: TemplateRef<any>;
  
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

  ngOnInit(): void {
    if (this.log) {
      console.log('Generando pagina opiniones');
    }
    this.autores = '';
    this.entrada.librosEntrada.forEach(libro => {
      libro.autores.forEach(autor => {
        this.autores += autor.nombre + ', ';
      });
    });
    this.autores = this.autores.substring(0, this.autores.length - 2);
    if (this.entrada.fechaAlta.valueOf() > new Date('2018/07/15').valueOf()){
      const metatituloPagina = this.entrada.tituloEntrada;
      this.titleService.setTitle(metatituloPagina);
    } else {
    const metatituloPagina =
      'Opinión saga - ' + this.entrada.sagasEntrada[0].nombreSaga;
    this.titleService.setTitle(metatituloPagina);
    }
    
    
    // Changing meta with name="description"
    const metadescripcion = this.entrada.fraseDescriptiva;
    const tag = { name: 'description', content: metadescripcion };
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
      href: 'https://momoko.es/opiniones/' + this.entrada.urlEntrada
    });
    this.linkService.removeTag('rel=amphtml');
    this.linkService.addTag({
      rel: 'amphtml',
      href:
        'https://momoko.es/amp/opiniones/' + this.entrada.urlEntrada
    });
    this.schema = JSON.parse(this.entrada.jsonLD);
    this.htmlContent = new Array();
    this.bookTemplates = new Array();
    if (this.entrada.contenidoEntrada.indexOf('book-template-angular') != -1) {
      let content = this.entrada.contenidoEntrada;
      let cont = 1;
      while (content.indexOf('book-template-angular') != -1) {
        const begin = content.indexOf(
          '<book-template-angular'
        );
        const end = content.indexOf(
          '</book-template-angular>'
        );
        this.htmlContent.push(
          content.substring(0, begin)
        );
        
        const book = content.substring(begin, end);
        this.htmlContent.push(
          "<div id=\"bookTemplate" + cont +"\" class=\"bookTemplate" + cont +"\">Book" + cont +"</div>"
        );
        
        this.bookTemplates.push(book);
        content = content.substring(end + 24);
        cont++;
      }
      this.htmlContent.push(content);
    } else {
      this.htmlContent.push(this.entrada.contenidoEntrada);
    }
    this.content = "";
    this.htmlContent.forEach(content => {
      this.content += content; 
    });
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
    if (this.log) {
      console.log('COLLAGE');
    }
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
