import { Autor } from './autor';
import { Libro } from 'app/dtos/libro';
import { User } from 'app/dtos/user';
import { Etiqueta } from 'app/dtos/etiqueta';

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
