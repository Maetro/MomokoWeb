import { DatoEntrada } from './datoEntrada';
import { Libro } from './libro';
import { Author } from './autor';
import { Genero } from './genre/genero';
import { Editorial } from './editorial';

export class Saga {
  sagaId: number;
  nombreSaga: string;
  urlSaga: string;
  imagenSaga: string;
  notaSaga: number;
  resumen: string;
  urlsLibrosSaga: string[];
  numeroVolumenes: number;
  estaTerminada: boolean;
  dominaLibros: boolean;
  tipoSaga: string;
  librosSaga: Libro[];
  entradasSaga: DatoEntrada[];
  autores: Author[];
  generos: Genero[];
  editorial: Editorial;
  notaMomoko: number;
  score: number;
}
