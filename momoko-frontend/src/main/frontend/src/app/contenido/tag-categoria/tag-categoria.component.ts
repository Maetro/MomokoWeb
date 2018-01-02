import { Component, OnInit, Input } from '@angular/core';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-tag-categoria',
  templateUrl: './tag-categoria.component.html',
  styleUrls: ['./tag-categoria.component.css']
})
export class TagCategoriaComponent implements OnInit {

  private log = environment.log;

  @Input() urlEntrada: string;

  @Input() nombreCategoria: string;

  colorCategoria: string;

  constructor() { }

  ngOnInit() {
    if (this.log) {
      console.log('Creando categoria tag');
    }
    if (this.nombreCategoria === 'Noticia') {
      this.colorCategoria = 'orange';
    } else if (this.nombreCategoria === 'Análisis') {
      this.colorCategoria = 'black';
    } else if (this.nombreCategoria === 'Misceláneos') {
      this.colorCategoria = 'blue';
    } else if (this.nombreCategoria === 'Vídeo') {
      this.colorCategoria = 'red';
    } else {
      this.colorCategoria = 'black';
    }

  }

}
