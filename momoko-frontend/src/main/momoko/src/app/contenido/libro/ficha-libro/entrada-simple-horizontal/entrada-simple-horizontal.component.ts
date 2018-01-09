import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { EntradaSimple } from '../../../../dtos/entradaSimple';

@Component({
  selector: 'app-entrada-simple-horizontal',
  templateUrl: './entrada-simple-horizontal.component.html',
  styleUrls: ['./entrada-simple-horizontal.component.css']
})
export class EntradaSimpleHorizontalComponent implements OnInit {

  private log = environment.log;

  @Input() entradas: EntradaSimple[];

  constructor() {}

  ngOnInit() {
  }

}

