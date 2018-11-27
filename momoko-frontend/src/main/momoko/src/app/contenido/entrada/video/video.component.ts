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
export class VideoComponent implements OnInit {

  private log = environment.log;

  @Input() entrada: Entrada;

  @Input() entradaAnteriorYSiguiente: EntradaSimple[];

  @Input() cuatroPostPequenosConImagen: EntradaSimple[];

  @Input() librosParecidos: LibroSimple[];

  @Input() comentarios: Comentario[];

  tituloSeccionLibros = 'Otros libros parecidos';

  backgroundImage = '/assets/style/images/art/parallax2.jpg';

  codigoVideo: string;

  safeURL: SafeUrl;

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

}
