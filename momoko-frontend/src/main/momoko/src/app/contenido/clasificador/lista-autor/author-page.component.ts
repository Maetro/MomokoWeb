import { OnInit, Component, Inject, PLATFORM_ID } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Author } from 'app/dtos/autor';
import { LibroSimple } from 'app/dtos/libroSimple';
import { ClasificadorService } from 'app/services/clasificador.service';
import { UtilService } from 'app/services/util/util.service';
import { environment } from 'environments/environment';
import { AuthorPageResponse } from './dtos/author-page-response';
import { isPlatformBrowser } from '@angular/common';


@Component({
  selector: 'app-author-page',
  templateUrl: "./author-page.component.html",
  styleUrls: ["./author-page.component.scss"]
})
export class AuthorPageComponent implements OnInit {

  isMobile = false;
  private log = environment.log;
  private url: string;
  suscriptor: any;
  authorBooks: LibroSimple[];
  author: Author;

  paginaActual: number;

  constructor(private clasificadorService: ClasificadorService, private route: ActivatedRoute,
    private router: Router, private titleService: Title, private metaService: Meta, private util: UtilService,
    @Inject(PLATFORM_ID) private platformId: Object) { }

  ngOnInit() {
    if (this.log) {
      console.log('Creando pagina del autor');
    }
    this.route.data.subscribe(data => {
        this.authorBooks = data.authorPage.authorBooks;
        this.author = data.authorPage.author;
        
        this.util.removeAllTags(this.metaService);

        const metatituloPagina = 'Autor: ' + this.author.name + ': Libros en momoko del autor: ' +
           this.author.name ;

        this.titleService.setTitle('Momoko - Autor: ' + this.author.name);
        const tag = {
          name: 'description', content: metatituloPagina
        };
        this.metaService.addTag(tag, false);
    });
    if (isPlatformBrowser(this.platformId)) {
      if (window.screen.width < 768) {
        this.isMobile = true;
      }
    }
  }

  onChangeOrder(event) {
    if (this.log) {
      console.log(event);
    }
  }

}
