import { EntradaSimple } from 'app/dtos/entradaSimple';
import { Categoria } from 'app/dtos/categoria';
import { Libro } from 'app/dtos/libro';

export class ObtenerPaginaLibroNoticiasResponse {
      libro: Libro;
      noticias: EntradaSimple[];
      numeroEntradas: number;
}
