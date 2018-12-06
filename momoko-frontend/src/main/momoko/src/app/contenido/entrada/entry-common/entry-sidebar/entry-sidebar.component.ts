import { Component, OnInit, Input } from "@angular/core";
import { environment } from "environments/environment";
import { LibroSimple } from "app/dtos/libroSimple";
import { Entrada } from "app/dtos/entrada";
import { Libro } from "app/dtos/libro";
import { DatoEntrada } from "app/dtos/datoEntrada";

@Component({
    selector: 'app-entry-sidebar',
    templateUrl: './entry-sidebar.component.html',
    styleUrls: ['./entry-sidebar.component.scss']
})
export class EntrySidebarComponent implements OnInit {

    @Input() entry: Entrada;

    @Input() books: Libro[];

    @Input() librosParecidos: LibroSimple[]; 

    @Input() datosEntrada: DatoEntrada[];

    @Input() parentType: string;

    tituloSeccionLibros = 'Otros libros parecidos';

    private log = environment.log;

    constructor() { }

    ngOnInit(): void {
    }
}