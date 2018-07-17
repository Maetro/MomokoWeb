import { Genero } from "./genero";
import { EntradaSimple } from "../entradaSimple";
import { LibroSimple } from "../libroSimple";



export class GenrePageResponse {

    genero: Genero;
    tresUltimasEntradasConLibro: EntradaSimple[];
    nueveLibrosGenero: LibroSimple[];
    numeroLibros: number;
}
