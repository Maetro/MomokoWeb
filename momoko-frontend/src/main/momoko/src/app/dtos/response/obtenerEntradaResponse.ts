

import { Comentario } from '../comentario';
import { Entrada } from '../entrada';
import { LibroSimple } from '../libroSimple';
import { EntradaSimple } from './../entradaSimple';

export class ObtenerEntradaResponse {
  entrada: Entrada;
  cincoLibrosParecidos: LibroSimple[];
  comentarios: Comentario[];

  // Miscelaneos
  obtenerEntradaAnteriorYSiguiente: EntradaSimple[];
  cuatroPostPequenosConImagen: EntradaSimple[];
}
