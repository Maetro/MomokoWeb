import { Component, OnInit, Input } from '@angular/core';
import { EntradaSimple } from '../../../../dtos/entradaSimple';

@Component({
  selector: 'app-entradas-sidebar',
  templateUrl: './entradas-sidebar.component.html',
  styleUrls: ['./entradas-sidebar.component.css']
})
export class EntradasSidebarComponent implements OnInit {

  constructor() { }

  @Input() entradas: EntradaSimple[];

  ngOnInit() {
  }

}
