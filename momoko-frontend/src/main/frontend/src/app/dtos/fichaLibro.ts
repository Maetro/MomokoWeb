import { LibroSimple } from './libroSimple';
import { EntradaSimple } from './entradaSimple';
import { Libro } from './libro';

export class FichaLibro {
  libro: Libro;
  tresUltimasEntradas: EntradaSimple[];
  cincoLibrosParecidos: LibroSimple[];
}
