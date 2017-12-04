import { Component, OnInit, Input } from '@angular/core';
import { Entrada } from 'app/dtos/entrada';
import { EntradaSimple } from 'app/dtos/entradaSimple';

@Component({
  selector: 'app-noticia',
  templateUrl: './noticia.component.html',
  styleUrls: ['./noticia.component.css']
})
export class NoticiaComponent implements OnInit {

    @Input() entrada: Entrada;

    @Input() entradaAnteriorYSiguiente: EntradaSimple[];

    @Input() cuatroPostPequenosConImagen: EntradaSimple[];

    backgroundImage = '/assets/style/images/art/parallax2.jpg';

    constructor() { }

    ngOnInit() {
    }


}
