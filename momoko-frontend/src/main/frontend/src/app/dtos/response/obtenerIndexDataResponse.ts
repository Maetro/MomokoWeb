
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { LibroSimple } from 'app/dtos/libroSimple';
import { Menu } from 'app/dtos/menu';
import { LibroEntradaSimple } from 'app/dtos/simples/libroEntradaSimple';

export class ObtenerIndexDataResponse {
  ultimasEntradas: EntradaSimple[];
  librosMasVistos: LibroSimple[];
  ultimoComicAnalizado: LibroEntradaSimple;
  ultimosAnalisis: LibroSimple[];
}
