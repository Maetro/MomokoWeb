import { Component, OnInit, Input } from "@angular/core";
import { environment } from "environments/environment";
import { EntradaSimple } from "app/dtos/entradaSimple";
import { Comentario } from "app/dtos/comentario";
import { Entrada } from "app/dtos/entrada";

@Component({
    selector: 'app-entry-footer',
    templateUrl: './entry-footer.component.html',
    styleUrls: ['./entry-footer.component.scss']
})
export class EntryFooterComponent implements OnInit {

    @Input() entry: Entrada;

    @Input() cuatroPostPequenosConImagen: EntradaSimple[];

    @Input() comentarios: Comentario[];

    @Input() entradaAnteriorYSiguiente: EntradaSimple[];

    private log = environment.log;

    constructor() { }

    ngOnInit(): void {
    }
}