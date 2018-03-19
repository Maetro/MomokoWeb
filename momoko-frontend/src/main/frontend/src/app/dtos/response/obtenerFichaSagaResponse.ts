import { Saga } from '../saga';
import { Libro } from '../libro';
import { EntradaSimple } from '../entradaSimple';
import { LibroSimple } from '../libroSimple';

export class ObtenerFichaSagaResponse {

  saga: Saga;
  librosSaga: Libro[];
  tresUltimasEntradas: EntradaSimple[];
  cincoLibrosParecidos: LibroSimple[];

}
