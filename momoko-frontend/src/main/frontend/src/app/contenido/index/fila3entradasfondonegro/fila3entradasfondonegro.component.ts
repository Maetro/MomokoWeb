import { CommonModule } from '@angular/common';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { environment } from 'environments/environment';

declare var $: any;

@Component({
  selector: 'app-fila3entradasfondonegro',
  templateUrl: './fila3entradasfondonegro.component.html',
  styleUrls: ['./fila3entradasfondonegro.component.css']
})
export class Fila3entradasfondonegroComponent implements OnInit, AfterViewInit {

  private log = environment.log;

  entradas: EntradaSimple[];

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {

    let maxHeight = 0;
    $('.zonablanca').each(function (index) {

      if (maxHeight < $(this).height()) {
        maxHeight = $(this).height();
      }
    });
    if (this.log) {
      console.log(maxHeight);
    }
    $('.zonablanca').css('height', maxHeight + 15);
  }

  loadEntradas(entradasSimples: EntradaSimple[]) {
    this.entradas = entradasSimples;
  }

}
