import { LibroSimple } from '../../dtos/libroSimple';
import { Component, OnInit, Input } from '@angular/core';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  private log = environment.log;

  @Input() libros: LibroSimple[];

  @Input() tituloSeccionLibros: string;

  constructor() { }

  ngOnInit() {

  }

}
