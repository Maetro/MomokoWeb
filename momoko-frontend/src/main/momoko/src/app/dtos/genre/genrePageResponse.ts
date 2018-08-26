import { Genero } from "./genero";
import { EntradaSimple } from "../entradaSimple";
import { LibroSimple } from "../libroSimple";
import { Filter } from "../filter/filter";



export class GenrePageResponse {

    genero: Genero;
    tresUltimasEntradasConLibro: EntradaSimple[];
    nueveLibrosGenero: LibroSimple[];
    numeroLibros: number;
    applicableFilters: Filter[];
}
