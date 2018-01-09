import { Libro } from "../libro";
import { EntradaSimple } from "../entradaSimple";



export class ObtenerPaginaLibroNoticiasResponse {
      libro: Libro;
      noticias: EntradaSimple[];
      numeroEntradas: number;
}
