import { Component, OnInit, Input } from '@angular/core';
import { EntradaSimple } from 'app/dtos/entradaSimple';

@Component({
  selector: 'app-entrada-simple-horizontal',
  templateUrl: './entrada-simple-horizontal.component.html',
  styleUrls: ['./entrada-simple-horizontal.component.css']
})
export class EntradaSimpleHorizontalComponent implements OnInit {

  @Input() entradas: EntradaSimple[];

  constructor() {}

  ngOnInit() {
  }

}
