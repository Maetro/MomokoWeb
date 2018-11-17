import { DatoEntrada } from './datoEntrada';
import { Etiqueta } from './etiqueta';
import { Libro } from './libro';
import { Redactor } from './redactor';
import { Saga } from './saga';

export class Entrada {
  entradaId: number;
  redactor: Redactor;
  urlEntrada: string;
  entryType: EntryTypeEnum;
  tituloEntrada: string;
  contenidoEntrada: string;
  resumenEntrada: string;
  entryStatus: EntryStatusEnum;
  permitirComentarios: boolean;
  padreEntrada: Entrada;
  librosEntrada: Libro[];
  sagasEntrada: Saga[];
  numeroComentarios: number;
  estadoEntrada: number;
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
  nombresSagasEntrada: string[];
  urlMenuLibro: string;
  visitas: number;
  jsonLD: string;
  datosEntrada: DatoEntrada[];
}

export enum EntryStatusEnum {
  DRAFT,
  PUBLISHED,
  DELETED
}

export enum EntryTypeEnum {
  NEWS,
  OPINION,
  MISCELLANEOUS,
  VIDEO,
  SPECIAL
}
