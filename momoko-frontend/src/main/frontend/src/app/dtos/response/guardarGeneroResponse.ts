import { Genero } from 'app/dtos/genero';

export class GuardarGeneroResponse {
  estadoGuardado: string;
  generoDTO: Genero;
  listaErroresValidacion: string[];
}
