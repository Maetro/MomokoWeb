import { Autor } from './autor';
import { Libro } from 'app/dtos/libro';
import { User } from 'app/dtos/user';

export class Entrada {
  entradaId: number;
  autor: User;
  urlEntrada: string;
  tipoEntrada: string;
  tituloEntrada: string;
  contenidoEntrada: string;
  resumenEntrada: string;
  estadoEntrada: number;
  permitirComentarios: boolean;
  padreEntrada: Entrada;
  libroEntrada: Libro;
  numeroComentarios: number;
  orden: number;
}
