import { CommonModule } from '@angular/common';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { EntradaSimple } from 'app/dtos/entradaSimple';

declare var $: any;

@Component({
  selector: 'app-fila3entradasfondonegro',
  templateUrl: './fila3entradasfondonegro.component.html',
  styleUrls: ['./fila3entradasfondonegro.component.css']
})
export class Fila3entradasfondonegroComponent implements OnInit, AfterViewInit {


  entradas: EntradaSimple[];

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {

     let maxHeight = 0;
     $('.zonablanca').each(function( index ) {

       if (maxHeight < $(this).height()) {
         maxHeight = $(this).height();
       }
     });
     console.log(maxHeight);
     $('.zonablanca').css('height', maxHeight + 15);
   }

  loadEntradas(entradasSimples: EntradaSimple[]) {
    this.entradas = entradasSimples;
  }

}
