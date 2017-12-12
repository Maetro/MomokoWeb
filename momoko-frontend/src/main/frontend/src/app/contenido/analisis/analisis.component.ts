import { Comentario } from 'app/dtos/comentario';
import { Router } from '@angular/router';
import { Component, OnInit, Input, OnDestroy, AfterViewInit } from '@angular/core';
import { Entrada } from 'app/dtos/entrada';
import { EntradaService } from 'app/services/entrada.service';
import { ActivatedRoute } from '@angular/router';
import { Libro } from 'app/dtos/libro';
import { LibroSimple } from 'app/dtos/libroSimple';

declare var $: any;

@Component({
  selector: 'app-analisis',
  templateUrl: './analisis.component.html',
  styleUrls: ['./analisis.component.css']
})

export class AnalisisComponent implements OnInit, AfterViewInit {



    @Input() entrada: Entrada;

    @Input() autores: string

    @Input() titulo: string;

    @Input() librosParecidos: LibroSimple[];

    @Input() comentarios: Comentario[];

    constructor() { }

    ngOnInit(): void {
        console.log('Generando pagina analisis');
        this.autores = '';
        this.entrada.librosEntrada.forEach(libro => {
          libro.autores.forEach(autor => {
            this.autores += autor.nombre + ', ';
          });
        });
        this.autores = this.autores.substring(0, this.autores.length - 2);
    }

    ngAfterViewInit(): void {
      $('.light-gallery').lightGallery({
        thumbnail: false,
        selector: '.lgitem',
        animateThumb: true,
        showThumbByDefault: false,
        download: false,
        autoplayControls: false,
        zoom: false,
        fullScreen: false,
        thumbWidth: 100,
        thumbContHeight: 80,
        hash: false,
        videoMaxWidth: '1000px'
    });
    }


}
