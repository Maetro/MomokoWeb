import { Component, OnInit, Input } from '@angular/core';
import { LibroSimple } from 'app/dtos/libroSimple';

@Component({
  selector: 'app-libros-horizontal',
  templateUrl: './libros-horizontal.component.html',
  styleUrls: ['./libros-horizontal.component.css']
})
export class LibrosHorizontalComponent implements OnInit {

  @Input() titulo: string;

  @Input() libros: LibroSimple;

  constructor() { }

  ngOnInit() {
  }

}
