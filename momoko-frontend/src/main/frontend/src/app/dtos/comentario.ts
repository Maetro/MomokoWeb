import { User } from './user';

export class Comentario {
  comentarioId: number;
  autor: User;
  urlEntrada: string;
  votosPositivos: number;
  votosNegativos: number;
  textoComentario: string;
  fecha: Date;
  comentariosHijo: Comentario[];
  entradaId: number;
  esSpoiler: boolean;
  esBan: boolean;
}
