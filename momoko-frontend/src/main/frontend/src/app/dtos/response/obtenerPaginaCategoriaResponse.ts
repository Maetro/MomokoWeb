
import { LibroSimple } from 'app/dtos/libroSimple';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { Categoria } from 'app/dtos/categoria';

export class ObtenerPaginaCategoriaResponse {

      categoria: Categoria;
      entradasCategoria: EntradaSimple[];
      numeroEntradas: number;
    }
