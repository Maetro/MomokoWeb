import { EntradaSimple } from 'app/dtos/entradaSimple';
import { LibroSimple } from 'app/dtos/libroSimple';
import { Menu } from 'app/dtos/menu';

export class IndexData {
  ultimasEntradas: EntradaSimple[];
  librosMasVistos: LibroSimple[];
}
