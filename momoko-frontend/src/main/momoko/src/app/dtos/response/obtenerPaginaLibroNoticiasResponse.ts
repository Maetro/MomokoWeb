import { Libro } from "../libro";
import { EntradaSimple } from "../entradaSimple";



export class ObtenerPaginaColeccionLibroResponse {
      libro: Libro;
      noticias: EntradaSimple[];
      numeroEntradas: number;
}
