import { DatoEntrada } from './datoEntrada';
import { Libro } from './libro';

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
}
