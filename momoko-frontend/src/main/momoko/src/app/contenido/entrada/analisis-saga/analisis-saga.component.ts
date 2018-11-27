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
import { EntradaSimple } from 'app/dtos/entradaSimple';

declare var $: any;

@Component({
  selector: 'app-analisis-saga',
  templateUrl: './analisis-saga.component.html',
  styleUrls: ['./analisis-saga.component.css']
})
export class AnalisisSagaComponent implements OnInit {
  private log = environment.log;

  @Input() entrada: Entrada;

  @Input() autores: string;

  @Input() librosParecidos: LibroSimple[];

  @Input() comentarios: Comentario[];

  @Input() cuatroPostPequenosConImagen: EntradaSimple[];

  @Input() entradaAnteriorYSiguiente: EntradaSimple[];

  sectionTitle: string;

  @ViewChild('book-template-angular')
  bookTemplate: TemplateRef<any>;
  
  tituloSeccionLibros = 'Otros libros parecidos';

  schema;

  constructor(
    private titleService: Title,
    private metaService: Meta,
    private linkService: LinkService
  ) {}

  ngOnInit(): void {
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
    if(this.entrada.sagasEntrada[0].tipoSaga == 'Saga'){
      this.sectionTitle = 'Reseña saga: ' + this.entrada.sagasEntrada[0].nombreSaga;
    } else {
      this.sectionTitle = 'Reseña colección: ' + this.entrada.sagasEntrada[0].nombreSaga;
    }
  }
}
