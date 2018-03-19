import { Saga } from 'app/dtos/saga';

export class GuardarSagaResponse {
  estadoGuardado: string
  sagaDTO: Saga;
  listaErroresValidacion: string[];
}
