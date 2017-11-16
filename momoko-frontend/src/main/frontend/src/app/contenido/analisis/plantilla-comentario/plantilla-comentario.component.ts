import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Comentario } from 'app/dtos/comentario';

@Component({
  selector: 'app-plantilla-comentario',
  templateUrl: './plantilla-comentario.component.html',
  styleUrls: ['./plantilla-comentario.component.css']
})
export class PlantillaComentarioComponent implements OnInit {

  @Input() comentario: Comentario;

  @Output() onSelectedComentario = new EventEmitter<Comentario>();

  constructor() { }

  ngOnInit() {
  }

  seleccionarComentario(comentario: Comentario) {
    console.log(comentario);
    this.onSelectedComentario.emit(this.comentario);
  }

  onSeleccionarComentario(comentario: Comentario) {
    console.log('Viene de mi hijo');
    this.onSelectedComentario.emit(comentario);
  }

}
