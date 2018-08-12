import { Etiqueta } from '../etiqueta';
import { LibroSimple } from '../libroSimple';
import { EntradaSimple } from '../entradaSimple';
import { Categoria } from '../categoria';

export class ObtenerPaginaEtiquetaResponse {

      etiqueta: Etiqueta;
      entradasEtiqueta: EntradaSimple[];
      numeroEntradas: number;
    }
