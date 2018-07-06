import { DatoEntrada } from './datoEntrada';
import { Etiqueta } from './etiqueta';
import { Libro } from './libro';
import { Redactor } from './redactor';
import { Saga } from './saga';

export class Entrada {
  entradaId: number;
  redactor: Redactor;
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
  sagasEntrada: Saga[];
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
  jsonLD: string;
  datosEntrada: DatoEntrada[];
}
