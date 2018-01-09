import { Comentario } from "../comentario";

export class GuardarComentarioResponse {
  estadoGuardado: string;
  comentario: Comentario;
  listaErroresValidacion: string[];
}
