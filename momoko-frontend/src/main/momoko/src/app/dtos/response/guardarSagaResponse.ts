import { Saga } from "../saga";


export class GuardarSagaResponse {
  estadoGuardado: string
  sagaDTO: Saga;
  listaErroresValidacion: string[];
}
