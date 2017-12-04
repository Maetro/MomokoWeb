import { Component, OnInit, Input } from '@angular/core';
import { Entrada } from 'app/dtos/entrada';
import { EntradaSimple } from 'app/dtos/entradaSimple';

@Component({
  selector: 'app-miscelaneos',
  templateUrl: './miscelaneos.component.html',
  styleUrls: ['./miscelaneos.component.css']
})
export class MiscelaneosComponent implements OnInit {

  @Input() entrada: Entrada;

  @Input() entradaAnteriorYSiguiente: EntradaSimple[];

  @Input() cuatroPostPequenosConImagen: EntradaSimple[];

  backgroundImage = '/assets/style/images/art/parallax2.jpg';

  constructor() { }

  ngOnInit() {
  }

}
