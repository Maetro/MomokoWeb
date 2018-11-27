import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { Comentario } from '../../../../dtos/comentario';
import { Entrada } from '../../../../dtos/entrada';
import { ComentarioRequest } from '../../../../dtos/request/comentarioRequest';
import { ComentariosService } from '../../../../services/comentarios.service';
import { PlantillaComentarioComponent } from './plantilla-comentario/plantilla-comentario.component';

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
        if (this.nuevoComentario.comentarioRespuesta){
          if (!this.comentarioAResponder.comentariosHijo){
            this.comentarioAResponder.comentariosHijo = [];
          }
          this.comentarioAResponder.comentariosHijo.push(res.comentario);
        } else {
          this.comentarios.push(res.comentario);
        }
        this.nuevoComentario = new ComentarioRequest();
        this.comentarioAResponder = null;
        this.comentarioSeleccionado = false;
        this.entrada.numeroComentarios++;
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

