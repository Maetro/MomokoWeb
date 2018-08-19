import { Redactor } from "../../../dtos/redactor";


export class GuardarRedactorResponse {
  estadoGuardado: string;
  redactor: Redactor;
  listaErroresValidacion: string[];
}
