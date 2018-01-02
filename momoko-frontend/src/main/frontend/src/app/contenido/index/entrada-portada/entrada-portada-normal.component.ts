import { EntradaSimple } from 'app/dtos/entradaSimple';
import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { EntradaPortada } from 'app/contenido/index/entrada-portada/entrada-portada.model';
import { environment } from 'environments/environment';

@Component({
  templateUrl: './entrada-portada-normal.component.html',
  styleUrls: ['./entrada-portada-normal.component.css']
})
export class EntradaPortadaNormalImplComponent implements EntradaPortada {

  private log = environment.log;

  @Input() data: EntradaSimple;

  selectedValue: number;

  constructor() { };

}
