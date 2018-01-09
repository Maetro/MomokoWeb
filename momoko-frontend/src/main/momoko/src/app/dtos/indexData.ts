import { EntradaSimple } from "./entradaSimple";
import { LibroEntradaSimple } from "./simples/libroEntradaSimple";
import { LibroSimple } from "./libroSimple";



export class IndexData {
  ultimasEntradas: EntradaSimple[];
  librosMasVistos: LibroSimple[];
  ultimoComicAnalizado: LibroEntradaSimple;
}
