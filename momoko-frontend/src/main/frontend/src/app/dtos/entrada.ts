import { User } from './user';
import { Etiqueta } from './etiqueta';
import { Libro } from 'app/dtos/libro';

export class Entrada {
  entradaId: number;
  autor: User;
  urlEntrada: string;
  tipoEntrada: number;
  tipoEntradaString: string;
  tituloEntrada: string;
  contenidoEntrada: string;
  resumenEntrada: string;
  estadoEntrada: number;
  permitirComentarios: boolean;
  padreEntrada: Entrada;
  libroEntrada: Libro;
  numeroComentarios: number;
  etiquetas: Etiqueta[];
  orden: number;
  imagenDestacada: string;
  tituloLibroEntrada: string;
  fraseDescriptiva: string;
  urlVideo: string;
}
