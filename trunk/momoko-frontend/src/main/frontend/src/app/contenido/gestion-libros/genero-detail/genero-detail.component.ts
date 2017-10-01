import { LibroService } from './../../../services/libro.service';
import { Genero } from './../../../dtos/genero';
import { Component, OnInit, Input, Output, EventEmitter, SimpleChange } from '@angular/core';
import { Message } from 'primeng/primeng';


@Component({
  selector: 'app-genero-detail',
  templateUrl: './genero-detail.component.html'
})
export class GeneroDetailComponent implements OnInit {

    @Input() genero: Genero;

    @Output() onGeneroGuardado: EventEmitter<Genero> = new EventEmitter<Genero>();

    changeLog: string[] = [];

    esGeneroNuevo = false;

    msgs: Message[] = [];

    constructor(private libroService: LibroService) {
    }

    ngOnInit(): void {
    }


    guardarGenero(): void {
      this.libroService.guardarGenero(this.genero)
        .then(res => {

          this.showSuccess('Libro guardado correctamente');
          this.onGeneroGuardado.emit(this.genero);
        })
        .catch();
    }

    showSuccess(mensaje: string) {
      this.msgs = [];
      console.log(mensaje);
      this.msgs.push({ severity: 'success', summary: 'OK', detail: mensaje });
    }

}
