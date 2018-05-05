import { Editorial } from 'app/dtos/editorial';

export class GuardarEditorialResponse {
  estadoGuardado: string;
  editorial: Editorial;
  listaErroresValidacion: string[];
}
