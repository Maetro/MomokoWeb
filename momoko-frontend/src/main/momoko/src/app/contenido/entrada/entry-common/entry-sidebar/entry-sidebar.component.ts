import { Component, OnInit, Input } from "@angular/core";
import { environment } from "environments/environment";
import { LibroSimple } from "app/dtos/libroSimple";
import { Entrada } from "app/dtos/entrada";
import { Libro } from "app/dtos/libro";

@Component({
    selector: 'app-entry-sidebar',
    templateUrl: './entry-sidebar.component.html',
    styleUrls: ['./entry-sidebar.component.scss']
})
export class EntrySidebarComponent implements OnInit {

    @Input() entry: Entrada;

    @Input() book: Libro;

    @Input() librosParecidos: LibroSimple[]; 

    tituloSeccionLibros = 'Otros libros parecidos';

    private log = environment.log;

    constructor() { }

    ngOnInit(): void {
    }
}