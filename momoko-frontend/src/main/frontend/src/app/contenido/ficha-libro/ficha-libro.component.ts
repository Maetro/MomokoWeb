import { Component, OnInit, Input, OnDestroy, AfterViewInit } from '@angular/core';
import { Entrada } from '../../dtos/entrada';
import { Libro } from '../../dtos/libro';
import { LibroService } from '../../services/libro.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LibroSimple } from '../../dtos/libroSimple';
import { FichaLibro } from '../../dtos/fichaLibro';
import { EntradaSimple } from '../../dtos/entradaSimple';
import { environment } from 'environments/environment';
import { Title, Meta } from '@angular/platform-browser';

declare var $: any;

@Component({
  selector: 'app-ficha-libro',
  templateUrl: './ficha-libro.component.html',
  styleUrls: ['./ficha-libro.component.css']
})
export class FichaLibroComponent implements OnInit, AfterViewInit {

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
    private metaService: Meta) {

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

  ngAfterViewInit(): void {
    if (this.log) {
      console.log('Animar hacia arriba');
    }
    $('body,html').animate({
      scrollTop: 0
    }, 800);
  }

  mirarAnchura(): number {
    return this.anchura;
  }
}
