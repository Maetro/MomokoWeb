import { Genero } from "../genre/genero";
import { EntradaSimple } from "../entradaSimple";
import { LibroSimple } from "../libroSimple";
import { User } from "../user";
import { Redactor } from "../redactor";

export class ObtenerPaginaRedactorResponse {

    redactor: Redactor;
    nueveEntradasEditor: EntradaSimple[];
    numeroEntradas: number;
}
