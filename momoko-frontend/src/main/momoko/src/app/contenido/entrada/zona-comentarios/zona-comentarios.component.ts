import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Entrada } from '../../../dtos/entrada';
import { Comentario } from '../../../dtos/comentario';
import { PlantillaComentarioComponent } from './plantilla-comentario/plantilla-comentario.component';
import { ComentarioRequest } from '../../../dtos/request/comentarioRequest';
import { ComentariosService } from '../../../services/comentarios.service';
import { NgForm } from '@angular/forms';
import { Message } from 'primeng/components/common/api';

@Component({
  selector: 'app-zona-comentarios',
  templateUrl: './zona-comentarios.component.html',
  styleUrls: ['./zona-comentarios.component.css']
})
export class ZonaComentariosComponent implements OnInit {

  private log = environment.log;

  @Input() entrada: Entrada;

  @Input() comentarios: Comentario[];

  @ViewChild(PlantillaComentarioComponent) plantillaComentarioComponent: PlantillaComentarioComponent;

  nuevoComentario: ComentarioRequest;

  comentarioAResponder: Comentario;

  comentarioSeleccionado = false;

  mostrarMensaje = false;

  mostrarError = false;

  constructor(private comentariosService: ComentariosService) {
    this.nuevoComentario = new ComentarioRequest;

  }

  ngOnInit() {

  }

  onPublicarComentario() {
    if (this.log) {
      console.log('Test');
    }
    this.nuevoComentario.entradaId = this.entrada.entradaId;
    this.comentariosService.guardarComentario(this.nuevoComentario).subscribe(res => {
      if (res.estadoGuardado === 'CORRECTO') {
        this.mostrarMensaje = true;
        this.nuevoComentario = new ComentarioRequest();
        this.comentarioAResponder = null;
        setTimeout(() => this.mostrarMensaje = false, 4000);
      } else {
        this.mostrarError = true;
        setTimeout(() => this.mostrarError = false, 4000);
      }
    })
  }

  seleccionarComentario(comentario: Comentario, element) {
    if (this.log) {
      console.log('Padre');
      console.log(comentario);
    }
    this.comentarioSeleccionado = true;
    this.comentarioAResponder = comentario;
    this.nuevoComentario.comentarioRespuesta = comentario.comentarioId;
  }

  cerrarComentario() {
    this.comentarioSeleccionado = false;
    this.comentarioAResponder = null;
    this.nuevoComentario.comentarioRespuesta = null;
  }

}

