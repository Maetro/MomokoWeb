import { Autor } from './autor';
import { Libro } from 'app/dtos/libro';
import { User } from 'app/dtos/user';

export class Entrada {
  entradaId: number;
  autor: User;
  tituloEntrada: string;
  urlEntrada: string;
  tipoEntrada: string;
  contenidoEntrada: string;
  resumenEntrada: string;
  estadoEntrada: number;
  permitirComentarios: boolean;
  padreEntrada: Entrada;
  libroEntrada: string;
  numeroComentarios: number;
  etiquetas: string[];
  orden: number;
  imagenDestacada: string;
}
