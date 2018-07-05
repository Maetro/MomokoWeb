import { Saga } from "../saga";
import { EntradaSimple } from "../entradaSimple";

export class ObtenerPaginaSagaNoticiasResponse {
  saga: Saga;
  noticias: EntradaSimple[];
  numeroEntradas: number;
  datosEntrada: DatoEntrada[];
}
