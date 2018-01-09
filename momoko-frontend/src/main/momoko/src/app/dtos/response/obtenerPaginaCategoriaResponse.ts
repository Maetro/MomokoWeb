import { Categoria } from "../categoria";
import { EntradaSimple } from "../entradaSimple";




export class ObtenerPaginaCategoriaResponse {

      categoria: Categoria;
      entradasCategoria: EntradaSimple[];
      numeroEntradas: number;
    }
