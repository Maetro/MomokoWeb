import { LibroSimple } from './libroSimple';
import { EntradaSimple } from './entradaSimple';
import { Libro } from './libro';
import { Saga } from './saga';


export class FichaSaga {
  saga: Saga;
  tresUltimasEntradas: EntradaSimple[];
  tresUltimasEntradasLibros: EntradaSimple[];
  librosSaga: Libro[];
  cincoLibrosParecidos: LibroSimple[];
}