import { Comentario } from 'app/dtos/comentario';
import { Entrada } from './entrada';
import { LibroSimple } from './libroSimple';

export class FichaEntrada {
  entrada: Entrada;
  cincoLibrosParecidos: LibroSimple[];
  comentarios: Comentario[];
}
