import { Comentario } from 'app/dtos/comentario';

export class GuardarComentarioResponse {
  estadoGuardado: string;
  comentario: Comentario;
  listaErroresValidacion: string[];
}
