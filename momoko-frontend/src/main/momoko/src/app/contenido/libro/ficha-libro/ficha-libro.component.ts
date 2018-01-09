import { Component, OnInit, AfterViewInit, Inject, PLATFORM_ID } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Libro } from '../../../dtos/libro';
import { LibroSimple } from '../../../dtos/libroSimple';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { ActivatedRoute, Router } from '@angular/router';
import { Title, Meta } from '@angular/platform-browser';
import { FichaLibro } from '../../../dtos/fichaLibro';
import { LibroService } from '../../../services/libro.service';

@Component({
  selector: 'app-ficha-libro',
  templateUrl: './ficha-libro.component.html',
  styleUrls: ['./ficha-libro.component.css']
})
export class FichaLibroComponent implements OnInit {

  private log = environment.log;

  anchura: number;

  url: string;

  suscriptor: any;

  libro: Libro;

  librosParecidos: LibroSimple[];

  tresUltimasEntradas: EntradaSimple[];

  tituloSeccionLibros = 'Otros libros parecidos';

  constructor(
    private libroService: LibroService,
    private route: ActivatedRoute,
    private router: Router,
    private titleService: Title,
    private metaService: Meta, 
    @Inject(PLATFORM_ID) private platformId: Object) {

    }

  ngOnInit() {
    if (this.log) {
      console.log('Creando pagina de la entrada');
    }
    const columna = document.getElementById('mirarAnchura')
    const width = columna.offsetWidth;
    const style = window.getComputedStyle(columna);
    // tslint:disable-next-line:radix
    const margin = parseInt(style.paddingLeft) + parseInt(style.paddingRight);
    this.anchura = width - margin;
    this.route.data.subscribe((data: { fichaLibro: FichaLibro }) => {
      this.libro = data.fichaLibro.libro;
      this.librosParecidos = data.fichaLibro.cincoLibrosParecidos;
      this.tresUltimasEntradas = data.fichaLibro.tresUltimasEntradas;
      let autores = '';
      this.libro.autores.forEach(autor => {
        autores = autores + autor.nombre + ', '
      });
      autores = autores.substring(0, autores.length - 2);
      const titulo = 'Ficha de libro ' + this.libro.titulo;
      const metatituloPagina = 'Encuentra aquí toda la información sobre ' + this.libro.titulo +
        ' y sobre ' + autores;
      this.titleService.setTitle(titulo);
      // Changing meta with name="description"
      const tag = { name: 'description', content: metatituloPagina };
      const attributeSelector = 'name="description"';
      this.metaService.removeTag(attributeSelector);
      this.metaService.addTag(tag, false);
    });

  }

  mirarAnchura(): number {
    return this.anchura;
  }
}

