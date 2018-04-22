import { Redactor } from 'app/dtos/redactor';


export class GuardarRedactorResponse {
  estadoGuardado: string;
  redactor: Redactor;
  listaErroresValidacion: string[];
}
