import { DatoEntrada } from './../datoEntrada';
import { Libro } from "../libro";
import { EntradaSimple } from "../entradaSimple";
import { Saga } from "../saga";



export class ObtenerPaginaNoticiasSagaResponse {
      saga: Saga;
      datosEntrada: DatoEntrada[];
      noticias: EntradaSimple[];
      numeroEntradas: number;
}
