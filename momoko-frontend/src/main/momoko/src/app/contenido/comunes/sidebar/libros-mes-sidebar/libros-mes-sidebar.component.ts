import { Component, OnInit, Input } from "@angular/core";
import { environment } from "../../../../../environments/environment";
import { LibroSimple } from "../../../../dtos/libroSimple";



@Component({
  selector: 'app-libros-mes-sidebar',
  templateUrl: './libros-mes-sidebar.component.html',
  styleUrls: ['./libros-mes-sidebar.component.css']
})
export class LibrosMesSidebarComponent implements OnInit {

  private log = environment.log;

  @Input() libros: LibroSimple;

  constructor() { }

  ngOnInit() {
  }

}
