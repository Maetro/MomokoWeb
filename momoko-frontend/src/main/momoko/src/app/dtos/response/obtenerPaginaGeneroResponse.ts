import { Genero } from "../genero";
import { EntradaSimple } from "../entradaSimple";
import { LibroSimple } from "../libroSimple";



export class ObtenerPaginaGeneroResponse {

    genero: Genero;
    tresUltimasEntradasConLibro: EntradaSimple[];
    nueveLibrosGenero: LibroSimple[];
    numeroLibros: number;
}
