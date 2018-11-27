import { Component, OnInit, AfterViewInit, Input, Inject, PLATFORM_ID, ViewChild, TemplateRef } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Entrada } from '../../../dtos/entrada';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { LibroSimple } from '../../../dtos/libroSimple';
import { Title, Meta } from '@angular/platform-browser';
import { isPlatformBrowser } from '@angular/common';
import { Comentario } from '../../../dtos/comentario';
import { LinkService } from '../../../services/link.service';
import { UtilService } from '../../../services/util/util.service';

declare var $: any;

@Component({
  selector: 'app-noticia',
  templateUrl: './noticia.component.html',
  styleUrls: ['./noticia.component.css']
})
export class NoticiaComponent implements OnInit {

  private log = environment.log;

  @Input() entrada: Entrada;

  @Input() entradaAnteriorYSiguiente: EntradaSimple[];

  @Input() cuatroPostPequenosConImagen: EntradaSimple[];

  @Input() librosParecidos: LibroSimple[];

  @Input() comentarios: Comentario[];

  constructor(
    private titleService: Title, 
    private metaService: Meta, 
    private linkService: LinkService) { }

  ngOnInit(): void {
    const metatituloPagina = this.entrada.tituloEntrada;
    this.titleService.setTitle(metatituloPagina);
    // Changing meta with name="description"
    const tag = { name: 'description', content: this.entrada.fraseDescriptiva };

    this.metaService.addTag(tag, false);
    this.metaService.addTag({ name: 'og:type', content: 'article' });
    this.metaService.addTag({ name: 'og:title', content: this.entrada.tituloEntrada });
    this.metaService.addTag({ name: 'og:description', content: this.entrada.fraseDescriptiva });
    this.metaService.addTag({ name: 'og:image', content: this.entrada.imagenDestacada });
    this.linkService.removeTag('rel=canonical');
    this.linkService.addTag( { rel: 'canonical', href: 'https://momoko.es/noticia/' +  this.entrada.urlEntrada} );
    this.linkService.removeTag('rel=amphtml');
    this.linkService.addTag({ rel: 'amphtml', href: 'https://momoko.es/amp/noticia/' +this.entrada.urlEntrada} );
  }
}
