import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { EntradaSimple } from '../../../../dtos/entradaSimple';
import { EntradaPortada } from '../entrada-portada.model';

@Component({
  templateUrl: './entrada-portada-normal.component.html',
  styleUrls: ['./entrada-portada-normal.component.css']
})
export class EntradaPortadaNormalComponent implements EntradaPortada {

  private log = environment.log;

  @Input() data: EntradaSimple;

  selectedValue: number;

  constructor() { };

}