import { PlantillaComentarioComponent } from 'app/contenido/analisis/plantilla-comentario/plantilla-comentario.component';
import { Comentario } from 'app/dtos/comentario';
import { Component, OnInit, Input, ElementRef, ViewChild, Renderer2 } from '@angular/core';
import { ComentarioRequest } from 'app/dtos/request/comentarioRequest';
import { NgForm } from '@angular/forms';
import { Message } from 'primeng/components/common/api';
import { GrowlModule } from 'primeng/primeng';
import { ComentariosService } from 'app/services/comentarios.service';
import { Entrada } from 'app/dtos/entrada';
import { ScrollToService } from 'ng2-scroll-to-el';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-crear-comentario',
  templateUrl: './crear-comentario.component.html',
  styleUrls: ['./crear-comentario.component.css']
})
export class CrearComentarioComponent implements OnInit {

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
