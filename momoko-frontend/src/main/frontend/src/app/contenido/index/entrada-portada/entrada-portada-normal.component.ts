import { EntradaSimple } from 'app/dtos/entradaSimple';
import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { EntradaPortada } from 'app/contenido/index/entrada-portada/entrada-portada.model';

@Component({
  templateUrl: './entrada-portada-normal.component.html'
})
export class EntradaPortadaNormalImplComponent implements EntradaPortada {
  @Input() data: EntradaSimple;

  selectedValue: number;

  constructor() { };

}
