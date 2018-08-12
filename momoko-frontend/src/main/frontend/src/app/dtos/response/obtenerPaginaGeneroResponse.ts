import { LibroSimple } from '../libroSimple';
import { EntradaSimple } from '../entradaSimple';
import { Genero } from '../genero';

export class ObtenerPaginaGeneroResponse {

    genero: Genero;
    tresUltimasEntradasConLibro: EntradaSimple[];
    nueveLibrosGenero: LibroSimple[];
}
