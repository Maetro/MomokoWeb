import { Editorial } from "../../../dtos/editorial";


export class GuardarEditorialResponse {
  estadoGuardado: string;
  editorial: Editorial;
  listaErroresValidacion: string[];
}
