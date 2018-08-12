import { Editorial } from '../editorial';

export class GuardarEditorialResponse {
  estadoGuardado: string;
  editorial: Editorial;
  listaErroresValidacion: string[];
}
