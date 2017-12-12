import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Entrada } from 'app/dtos/entrada';
import { Libro } from 'app/dtos/libro';
import { LibroService } from 'app/services/libro.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LibroSimple } from 'app/dtos/libroSimple';
import { FichaLibro } from 'app/dtos/fichaLibro';
import { EntradaSimple } from 'app/dtos/entradaSimple';

@Component({
  selector: 'app-ficha-libro',
  templateUrl: './ficha-libro.component.html',
  styleUrls: ['./ficha-libro.component.css']
})
export class FichaLibroComponent implements OnInit {

  anchura: number;

  url: string;

  suscriptor: any;

  libro: Libro;

  librosParecidos: LibroSimple[];

  tresUltimasEntradas: EntradaSimple[];

  constructor(private libroService: LibroService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    console.log('Creando pagina de la entrada');
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
    });
  }


  mirarAnchura(): number {
    return this.anchura;
  }
}
