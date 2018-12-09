import { EntradaSimple } from "../entradaSimple";
import { LibroEntradaSimple } from "../simples/libroEntradaSimple";
import { LibroSimple } from "../libroSimple";

export class IndexDataResponse {
  lastOpinions: EntradaSimple[];
  lastNews: EntradaSimple[];
  lastMiscellaneous: EntradaSimple[];
  masonryEntries: EntradaSimple[];
  librosMasVistos: LibroSimple[];
  ultimoComicAnalizado: LibroEntradaSimple;
  ultimosAnalisis: LibroSimple[];
}
