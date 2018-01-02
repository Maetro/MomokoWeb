import { Etiqueta } from './../etiqueta';
import { LibroSimple } from 'app/dtos/libroSimple';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { Categoria } from 'app/dtos/categoria';

export class ObtenerPaginaEtiquetaResponse {

      etiqueta: Etiqueta;
      entradasEtiqueta: EntradaSimple[];
      numeroEntradas: number;
    }
