
import { EntradaSimple } from '../entradaSimple';
import { LibroSimple } from '../libroSimple';
import { Menu } from '../menu';
import { LibroEntradaSimple } from '../simples/libroEntradaSimple';

export class ObtenerIndexDataResponse {
  ultimasEntradas: EntradaSimple[];
  librosMasVistos: LibroSimple[];
  ultimoComicAnalizado: LibroEntradaSimple;
  ultimosAnalisis: LibroSimple[];
}
