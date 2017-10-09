
import { Component, OnInit } from '@angular/core';

import { Libro } from './../dtos/libro';
import { LibroService } from './../services/libro.service';
import { FileUploadService } from './../services/fileUpload.service';

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
