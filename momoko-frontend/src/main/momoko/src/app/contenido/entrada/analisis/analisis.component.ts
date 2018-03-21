import { Component, OnInit, AfterViewInit, Input, PLATFORM_ID, Inject } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Entrada } from '../../../dtos/entrada';
import { LibroSimple } from '../../../dtos/libroSimple';
import { Comentario } from '../../../dtos/comentario';
import { Title, Meta } from '@angular/platform-browser';
import { isPlatformBrowser } from '@angular/common';
import { LinkService } from '../../../services/link.service';

declare var $: any;

@Component({
  selector: 'app-analisis',
  templateUrl: './analisis.component.html',
  styleUrls: ['./analisis.component.css']
})

export class AnalisisComponent implements OnInit, AfterViewInit {

  private log = environment.log;

  @Input() entrada: Entrada;

  @Input() autores: string

  @Input() titulo: string;

  @Input() librosParecidos: LibroSimple[];

  @Input() comentarios: Comentario[];

  tituloSeccionLibros = 'Otros libros parecidos';

  schema 

  constructor(private titleService: Title, private metaService: Meta, @Inject(PLATFORM_ID) private platformId: Object,
  private linkService: LinkService) { }

  ngOnInit(): void {
    if (this.log) {
      console.log('Generando pagina analisis');
    }
    this.autores = '';
    this.entrada.librosEntrada.forEach(libro => {
      libro.autores.forEach(autor => {
        this.autores += autor.nombre + ', ';
      });
    });
    this.autores = this.autores.substring(0, this.autores.length - 2);

    const metatituloPagina = 'Análisis libro - ' + this.entrada.librosEntrada[0].titulo;
    this.titleService.setTitle(metatituloPagina);
    // Changing meta with name="description"
    const metadescripcion = this.entrada.fraseDescriptiva;
    const tag = { name: 'description', content: metadescripcion };
    const attributeSelector = 'name="description"';
    this.metaService.removeTag(attributeSelector);
    this.metaService.addTag(tag, false);
    this.metaService.addTag({ name: 'og:type', content: 'article' })  
    this.metaService.addTag({ name: 'og:title', content: this.entrada.tituloEntrada })  
    this.metaService.addTag({ name: 'og:description', content: this.entrada.fraseDescriptiva })  
    this.metaService.addTag({ name: 'og:image', content: this.entrada.imagenDestacada })  
    this.linkService.addTag( { rel: 'canonical', href: 'https://momoko.es/libro/' + 
    this.entrada.librosEntrada[0].urlLibro +'/resena/' +  this.entrada.urlEntrada} );
    this.schema = {
      "@context": "http://schema.org",
  "@type": "WebPage",
  "breadcrumb": "Books > Literature & Fiction > Classics",
  "mainEntity":{
          "@type": "Book",
          "author": "/author/jd_salinger.html",
          "bookFormat": "http://schema.org/Paperback",
          "datePublished": "1991-05-01",
          "image": "catcher-in-the-rye-book-cover.jpg",
          "inLanguage": "English",
          "isbn": "0316769487",
          "name": "The Catcher in the Rye",
          "numberOfPages": "224",
          "offers": {
            "@type": "Offer",
            "availability": "http://schema.org/InStock",
            "price": "6.99",
            "priceCurrency": "USD"
          },
          "publisher": "Little, Brown, and Company",
          "aggregateRating": {
            "@type": "AggregateRating",
            "ratingValue": "4",
            "reviewCount": "3077"
          },
          "review": [
            {
              "@type": "Review",
              "author": "John Doe",
              "datePublished": "2006-05-04",
              "name": "A masterpiece of literature",
              "reviewBody": "I really enjoyed this book. It captures the essential challenge people face as they try make sense of their lives and grow to adulthood.",
              "reviewRating": {
            "@type": "Rating",
            "ratingValue": "5"
           }
            },
            {
              "@type": "Review",
              "author": "Bob Smith",
              "datePublished": "2006-06-15",
              "name": "A good read.",
              "reviewBody": "Catcher in the Rye is a fun book. It's a good book to read.",
              "reviewRating": "4"
            }
          ]
        }
    };
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
    if (this.log) {
      console.log('COLLAGE');
    }
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