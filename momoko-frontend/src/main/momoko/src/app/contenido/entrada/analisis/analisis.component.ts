import { isPlatformBrowser } from '@angular/common';
import { AfterViewInit, Component, Inject, Input, OnInit, PLATFORM_ID, TemplateRef, ViewChild } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { environment } from '../../../../environments/environment';
import { Comentario } from '../../../dtos/comentario';
import { Entrada } from '../../../dtos/entrada';
import { LibroSimple } from '../../../dtos/libroSimple';
import { LinkService } from '../../../services/link.service';
import { EntradaSimple } from 'app/dtos/entradaSimple';



@Component({
  selector: 'app-analisis',
  templateUrl: './analisis.component.html',
  styleUrls: ['./analisis.component.scss']
})
export class AnalisisComponent implements OnInit {
  private log = environment.log;

  @Input()
  entrada: Entrada;

  @Input()
  librosParecidos: LibroSimple[];

  @Input()
  comentarios: Comentario[];

  @Input() cuatroPostPequenosConImagen: EntradaSimple[];

  @Input() entradaAnteriorYSiguiente: EntradaSimple[];

  authors: string;

  schema;

  constructor(
    private titleService: Title,
    private metaService: Meta,
    private linkService: LinkService
  ) {}

  ngOnInit(): void {
    if (this.log) {
      console.log('Generando pagina opiniones');
    }

    if (this.entrada.fechaAlta.valueOf() > new Date('2018/07/15').valueOf()) {
      const metatituloPagina = this.entrada.tituloEntrada;
      this.titleService.setTitle(metatituloPagina);
    } else {
      const metatituloPagina =
        'Análisis libro - ' + this.entrada.librosEntrada[0].titulo;
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
      href: 'https://momoko.es/amp/opiniones/' + this.entrada.urlEntrada
    });
    this.schema = JSON.parse(this.entrada.jsonLD);

    
  }


}
