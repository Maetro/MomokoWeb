import { DatoEntrada } from '../datoEntrada';
import { EntradaSimple } from "../entradaSimple";
import { Saga } from "../saga";



export class ObtenerPaginaColeccionSagaResponse {
      saga: Saga;
      datosEntrada: DatoEntrada[];
      entradas: EntradaSimple[];
      numeroEntradas: number;
}