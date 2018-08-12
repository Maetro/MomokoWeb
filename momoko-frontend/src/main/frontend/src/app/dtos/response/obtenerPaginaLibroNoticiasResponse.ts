import { EntradaSimple } from '../entradaSimple';
import { Categoria } from '../categoria';
import { Libro } from '../libro';

export class ObtenerPaginaLibroNoticiasResponse {
      libro: Libro;
      noticias: EntradaSimple[];
      numeroEntradas: number;
}
