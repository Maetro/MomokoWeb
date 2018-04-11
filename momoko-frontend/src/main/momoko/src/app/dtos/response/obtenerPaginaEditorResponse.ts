import { Genero } from "../genero";
import { EntradaSimple } from "../entradaSimple";
import { LibroSimple } from "../libroSimple";
import { User } from "../user";

export class ObtenerPaginaEditorResponse {

    autor: User;
    nueveEntradasEditor: EntradaSimple[];
    numeroLibros: number;
}
