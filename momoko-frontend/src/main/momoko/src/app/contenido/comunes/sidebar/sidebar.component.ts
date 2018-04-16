import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { LibroSimple } from '../../../dtos/libroSimple';
import { Router } from '@angular/router';
import { EntradaSimple } from '../../../dtos/entradaSimple';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  private log = environment.log;

  busqueda = "";

  @Input() libros: LibroSimple[];

  @Input() tituloSeccionLibros: string;

  @Input() entradas: EntradaSimple[];

  @Input() tituloSeccionEntradas: string;

  constructor(private router: Router) { }

  ngOnInit() {

  }

  buscarResultados(){
    if (this.log) {
      console.log('Buscar: ' + this.busqueda);
    }
    this.router.navigate(['/buscar/' + this.busqueda]);
    this.busqueda = "";
  }

}