import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { Entrada } from 'app/dtos/entrada';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { LibroSimple } from 'app/dtos/libroSimple';

declare var $: any;

@Component({
  selector: 'app-miscelaneos',
  templateUrl: './miscelaneos.component.html',
  styleUrls: ['./miscelaneos.component.css']
})
export class MiscelaneosComponent implements OnInit, AfterViewInit {

  @Input() entrada: Entrada;

  @Input() entradaAnteriorYSiguiente: EntradaSimple[];

  @Input() cuatroPostPequenosConImagen: EntradaSimple[];

  @Input() librosParecidos: LibroSimple[];

  backgroundImage = '/assets/style/images/art/parallax2.jpg';

  tituloSeccionLibros = 'Otros libros parecidos';

  constructor() { }

  ngOnInit() {
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

}
