import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { environment } from 'environments/environment';
import { Comentario } from 'app/dtos/comentario';


@Component({
  selector: 'app-plantilla-comentario',
  templateUrl: './plantilla-comentario.component.html',
  styleUrls: ['./plantilla-comentario.component.css']
})
export class PlantillaComentarioComponent implements OnInit {

  private log = environment.log;

  @Input() comentario: Comentario;

  @Output() onSelectedComentario = new EventEmitter<Comentario>();

  constructor() { }

  ngOnInit() {
  }

  seleccionarComentario(comentario: Comentario) {
    if (this.log) {
      console.log(comentario);
    }
    this.onSelectedComentario.emit(this.comentario);
  }

  onSeleccionarComentario(comentario: Comentario) {
    if (this.log) {
      console.log('Viene de mi hijo');
    }
    this.onSelectedComentario.emit(comentario);
  }

}
