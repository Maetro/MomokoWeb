import { User } from './user';
import { Etiqueta } from './etiqueta';

export class Entrada {
  entradaId: number;
  autor: User;
  urlEntrada: string;
  tipoEntrada: number;
  tituloEntrada: string;
  contenidoEntrada: string;
  resumenEntrada: string;
  estadoEntrada: number;
  permitirComentarios: boolean;
  padreEntrada: Entrada;
  libroEntrada: string;
  numeroComentarios: number;
  etiquetas: Etiqueta[];
  orden: number;
  imagenDestacada: string;
}
