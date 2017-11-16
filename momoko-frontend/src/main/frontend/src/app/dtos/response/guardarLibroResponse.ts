import { Libro } from 'app/dtos/libro';

export class GuardarLibroResponse {
  estadoGuardado: string
  libroDTO: Libro
  listaErroresValidacion: string[];
}
