import { Component, OnInit, Input } from '@angular/core';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { EntradaPortada } from 'app/contenido/index/entrada-portada/entrada-portada.model';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-entrada-portada-video',
  templateUrl: './entrada-portada-video.component.html',
  styleUrls: ['./entrada-portada-video.component.css']
})
export class EntradaPortadaVideoComponent implements EntradaPortada {

  private log = environment.log;

  @Input() data: EntradaSimple;

  constructor() {

  }

}
