import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Entrada } from '../../../dtos/entrada';
import { Comentario } from '../../../dtos/comentario';
import { PlantillaComentarioComponent } from './plantilla-comentario/plantilla-comentario.component';
import { ComentarioRequest } from '../../../dtos/request/comentarioRequest';
import { ComentariosService } from '../../../services/comentarios.service';
import { NgForm } from '@angular/forms';
import { Message } from 'primeng/components/common/api';
import { GrowlModule } from 'primeng/components/growl/growl';
import { ScrollToService } from 'ng2-scroll-to-el';

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

  msgs: Message[] = [];

  constructor(private comentariosService: ComentariosService, private scrollService: ScrollToService) {
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
        this.showSuccess('Comentario guardado correctamente');
      } else {
        this.showError(res.listaErroresValidacion);
      }
    })
  }

  showSuccess(mensaje: string) {
    this.msgs = [];
    if (this.log) {
      console.log(mensaje);
    }
    this.msgs.push({ severity: 'success', summary: 'OK', detail: mensaje });
  }

  showError(mensaje: string[]) {
    this.msgs = [];
    if (this.log) {
      console.log(mensaje);
    }
    let mensajeTotal = '';
    mensaje.forEach(element => {
      mensajeTotal += element + '<br/>';
    });
    if (this.log) {
      console.log(mensajeTotal);
    }
    this.msgs.push({ severity: 'error', summary: 'ERROR', detail: mensajeTotal });
  }

  seleccionarComentario(comentario: Comentario, element) {
    if (this.log) {
      console.log('Padre');
      console.log(comentario);
    }
    this.scrollToElement(element);
    this.comentarioSeleccionado = true;
    this.comentarioAResponder = comentario;
    this.nuevoComentario.comentarioRespuesta = comentario.comentarioId;
  }

  scrollToElement(element) {
    this.scrollService.scrollTo(element);
  }

  cerrarComentario() {
    this.comentarioSeleccionado = false;
    this.comentarioAResponder = null;
    this.nuevoComentario.comentarioRespuesta = null;
  }

}

