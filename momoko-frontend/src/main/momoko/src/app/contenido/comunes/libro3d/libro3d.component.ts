import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-libro3d',
  templateUrl: './libro3d.component.html',
  styleUrls: ['./libro3d.component.css']
})
export class Libro3dComponent implements OnInit {

  private log = environment.log;

  @Input() portada: string;

  @Input() anchura: number;
  @Input() anchuraPortada: number;
  @Input() alturaPortada: number;

  altura: number;

  alturapx: string;
  alturaderechapx: string;
  anchurapx: string;

  constructor() { }

  ngOnInit() {
    if (this.anchuraPortada > this.anchura) {
        // Hay que adaptar la imagen a la anchura disponible
        this.altura = (this.alturaPortada * this.anchura) / this.anchuraPortada;
    } else if (this.anchura > this.anchuraPortada) {
        this.anchura = this.anchuraPortada
        this.altura = this.alturaPortada;
    } else {
      this.altura = this.alturaPortada;
    }
    this.anchurapx = this.anchura + 'px';
    this.alturapx = this.altura + 'px';
    this.alturaderechapx = (this.altura - 8) + 'px';
  }

  cambiarAlturaAdaptada(altura: number) {
    this.altura = altura;
  }

}