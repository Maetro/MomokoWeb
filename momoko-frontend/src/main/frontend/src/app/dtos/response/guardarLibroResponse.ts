import { Libro } from '../libro';

export class GuardarLibroResponse {
  estadoGuardado: string
  libroDTO: Libro
  listaErroresValidacion: string[];
}
