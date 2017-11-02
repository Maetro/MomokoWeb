import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { EntradaSimple } from 'app/dtos/entradaSimple';

@Component({
  selector: 'app-fila3entradasfondonegro',
  templateUrl: './fila3entradasfondonegro.component.html',
  styleUrls: ['./fila3entradasfondonegro.component.css']
})
export class Fila3entradasfondonegroComponent implements OnInit {

  entradas: EntradaSimple[];

  constructor() { }

  ngOnInit() {
  }

  loadEntradas(entradasSimples: EntradaSimple[]) {
    this.entradas = entradasSimples;
  }

}
