import { Genero } from '../genero';

export class GuardarGeneroResponse {
  estadoGuardado: string;
  generoDTO: Genero;
  listaErroresValidacion: string[];
}
