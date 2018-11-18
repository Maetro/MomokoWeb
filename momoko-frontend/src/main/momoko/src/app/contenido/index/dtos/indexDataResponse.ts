import { EntradaSimple } from "../../../dtos/entradaSimple";
import { LibroSimple } from "../../../dtos/libroSimple";
import { LibroEntradaSimple } from "../../../dtos/simples/libroEntradaSimple";




export class IndexDataResponse {
  lastOpinions: EntradaSimple[];
  lastNews: EntradaSimple[];
  lastMiscellaneous: EntradaSimple[];
  librosMasVistos: LibroSimple[];
  ultimoComicAnalizado: LibroEntradaSimple;
  ultimosAnalisis: LibroSimple[];
}
