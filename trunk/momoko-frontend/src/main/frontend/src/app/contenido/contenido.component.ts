
import { Component, OnInit } from '@angular/core';

import { Libro } from './../dtos/libro';
import { LibroService } from './../libro.service';

@Component({
  selector: 'app-contenido',
  templateUrl: './contenido.component.html',
  styleUrls: ['./contenido.component.css']
})
export class ContenidoComponent implements OnInit {

  constructor() { }

    ngOnInit() {
    }

}
