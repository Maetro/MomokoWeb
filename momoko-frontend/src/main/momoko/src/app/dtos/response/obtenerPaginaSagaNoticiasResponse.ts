import { DatoEntrada } from './../datoEntrada';
import { EntradaSimple } from '../entradaSimple';
import { Saga } from '../saga';

export class ObtenerPaginaSagaNoticiasResponse {
  saga: Saga;
  noticias: EntradaSimple[];
  numeroEntradas: number;
  datosEntrada: DatoEntrada[];
}
