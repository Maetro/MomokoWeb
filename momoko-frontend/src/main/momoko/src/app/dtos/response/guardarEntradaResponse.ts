import { Entrada } from "../entrada";


export class GuardarEntradaResponse {
  estadoGuardado: string;
  entrada: Entrada;
  listaErroresValidacion: string[];
}
