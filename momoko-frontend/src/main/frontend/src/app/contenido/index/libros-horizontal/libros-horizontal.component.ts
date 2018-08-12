import { Component, OnInit, Input } from '@angular/core';
import { LibroSimple } from '../../../dtos/libroSimple';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-libros-horizontal',
  templateUrl: './libros-horizontal.component.html',
  styleUrls: ['./libros-horizontal.component.css']
})
export class LibrosHorizontalComponent implements OnInit {

  private log = environment.log;

  @Input() titulo: string;

  @Input() libros: LibroSimple;

  constructor() { }

  ngOnInit() {
  }

}
