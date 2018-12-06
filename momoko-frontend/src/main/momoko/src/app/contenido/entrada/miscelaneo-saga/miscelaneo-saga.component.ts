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
export class MiscelaneoSagaComponent implements OnInit {
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

  sectionTitle: string;

  schema;

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

    this.sectionTitle = 'Misceláneo saga: ' + this.entrada.sagasEntrada[0].nombreSaga;
  }


}
