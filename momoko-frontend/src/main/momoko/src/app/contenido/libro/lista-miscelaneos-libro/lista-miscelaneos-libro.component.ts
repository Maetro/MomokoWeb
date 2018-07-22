import { Component, OnInit } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { Libro } from '../../../dtos/libro';
import { LibroService } from '../../../services/libro.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Title, Meta } from '@angular/platform-browser';
import { ObtenerPaginaColeccionLibroResponse } from '../../../dtos/response/obtenerPaginaLibroNoticiasResponse';
import { DatoEntrada } from '../../../dtos/datoEntrada';

@Component({
  selector: 'app-lista-miscelaneos-libro',
  templateUrl: './lista-miscelaneos-libro.component.html',
  styleUrls: ['./lista-miscelaneos-libro.component.css']
})
export class ListaMiscelaneosLibroComponent implements OnInit {

  private log = environment.log;

  libro: Libro;
  noticias: EntradaSimple[];
  numeroEntradas: number;
  datosEntrada: DatoEntrada[];
  anchura: number;
  enLista: boolean;
  constructor(private libroService: LibroService, private route: ActivatedRoute, private router: Router,
    private titleService: Title, private metaService: Meta) { }

  ngOnInit() {
    const columna = document.getElementById('mirarAnchura')
    const width = columna.offsetWidth;
    const style = window.getComputedStyle(columna);
    // tslint:disable-next-line:radix
    const margin = parseInt(style.paddingLeft) + parseInt(style.paddingRight);
    this.anchura = width - margin;
    this.enLista = false;
    this.route.data.subscribe((coleccionLibro: { coleccionLibro: ObtenerPaginaColeccionLibroResponse }) => {
      this.libro = coleccionLibro.coleccionLibro.libro;
      this.noticias = coleccionLibro.coleccionLibro.noticias;
      this.numeroEntradas = coleccionLibro.coleccionLibro.numeroEntradas;
      this.datosEntrada = coleccionLibro.coleccionLibro.datoEntrada;
      let autores = '';
      this.libro.autores.forEach(autor => {
        autores = autores + autor.nombre + ', '
      });
      autores = autores.substring(0, autores.length - 2);
      const metatituloPagina = 'Encuentra aquí las últimas noticias sobre ' + this.libro.titulo +
      ' de ' + autores ;
    this.titleService.setTitle(metatituloPagina);
    this.metaService.removeTag('name="og:url"');
    this.metaService.removeTag('name="og:type"');
    this.metaService.removeTag('name="og:title"');
    this.metaService.removeTag('name="og:description"');
    this.metaService.removeTag('name="og:image"');
    this.metaService.addTag({ name: 'og:url', content: 'https://momoko.es/libro/' + this.libro.urlLibro + 'noticias' });
    this.metaService.addTag({ name: 'og:locale', content: 'es_ES' });
    this.metaService.addTag({ name: 'fb:app_id', content: '1932678757049258' });
    this.metaService.addTag({ name: 'og:type', content: 'article' });
    this.metaService.addTag({ name: 'og:title', content: this.libro.titulo });
    this.metaService.addTag({ name: 'og:description', content: this.libro.resumen});
    this.metaService.addTag({ name: 'og:image', content: this.libro.urlImagen });
    });
  }

  mirarAnchura(): number {
    return this.anchura;
  }


  activarEnLista() {
    this.enLista = true;
  }

  desactivarEnLista() {
    this.enLista = false;
  }

}

