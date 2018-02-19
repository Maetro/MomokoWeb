import { User } from './user';
import { Etiqueta } from './etiqueta';
import { Libro } from './libro';


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
  librosEntrada: Libro[];
  numeroComentarios: number;
  etiquetas: Etiqueta[];
  orden: number;
  imagenDestacada: string;
  titulosLibrosEntrada: string;
  fraseDescriptiva: string;
  fechaAlta: Date;
  urlVideo: string;
  tieneGaleria: boolean;
  editorNombre: string;
  conSidebar: boolean;
  enMenu: boolean;
  nombreMenuLibro: string;
  urlMenuLibro: string;
  visitas: number;
}
