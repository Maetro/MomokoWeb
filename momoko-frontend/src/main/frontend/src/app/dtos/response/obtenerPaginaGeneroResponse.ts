import { LibroSimple } from 'app/dtos/libroSimple';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { Genero } from 'app/dtos/genero';

export class ObtenerPaginaGeneroResponse {

    genero: Genero;
    tresUltimasEntradasConLibro: EntradaSimple[];
    nueveLibrosGenero: LibroSimple[];
}
