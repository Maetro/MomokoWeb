import { EntradaSimple } from 'app/dtos/entradaSimple';
import { Libro } from './../../dtos/libro';
import { Component, OnInit } from '@angular/core';
import { LibroService } from 'app/services/libro.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ObtenerPaginaLibroNoticiasResponse } from 'app/dtos/response/obtenerPaginaLibroNoticiasResponse';
import { environment } from 'environments/environment';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-lista-noticias-libro',
  templateUrl: './lista-noticias-libro.component.html',
  styleUrls: ['./lista-noticias-libro.component.css']
})
export class ListaNoticiasLibroComponent implements OnInit {

  private log = environment.log;

  libro: Libro;
  noticias: EntradaSimple[];
  numeroEntradas: number;
  anchura: number;
  enLista: boolean;
  constructor(private libroService: LibroService, private route: ActivatedRoute, private router: Router,
    private titleService: Title) { }

  ngOnInit() {
    const columna = document.getElementById('mirarAnchura')
    const width = columna.offsetWidth;
    const style = window.getComputedStyle(columna);
    // tslint:disable-next-line:radix
    const margin = parseInt(style.paddingLeft) + parseInt(style.paddingRight);
    this.anchura = width - margin;
    this.enLista = false;
    this.route.data.subscribe((noticiasLibro: { noticiasLibro: ObtenerPaginaLibroNoticiasResponse }) => {
      this.libro = noticiasLibro.noticiasLibro.libro;
      this.noticias = noticiasLibro.noticiasLibro.noticias;
      this.numeroEntradas = noticiasLibro.noticiasLibro.numeroEntradas;
      let autores = '';
      this.libro.autores.forEach(autor => {
        autores = autores + autor.nombre + ', '
      });
      autores = autores.substring(0, autores.length - 2);
      const metatituloPagina = 'Encuentra aquí las últimas noticias sobre ' + this.libro.titulo +
      ' de ' + autores ;
    this.titleService.setTitle(metatituloPagina);
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
