import { Entrada } from 'app/dtos/entrada';

export class GuardarEntradaResponse {
  estadoGuardado: string;
  entrada: Entrada;
  listaErroresValidacion: string[];
}
