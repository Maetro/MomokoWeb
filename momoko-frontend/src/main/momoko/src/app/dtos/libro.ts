import { Genero } from './genre/genero';
import { Editorial } from './editorial';
import { Author } from './autor';
import { DatoEntrada } from './datoEntrada';
import { Saga } from './saga';
import { Filter } from './filter/filter';

export class Libro {
  libroId: number;
  saga: Saga;
  editorial: Editorial;
  generos: Genero[];
  anoEdicion: number;
  anoPublicacion: number;
  numeroPaginas: number;
  citaLibro: string;
  resumen: string;
  enlaceAmazon: string;
  urlImagen: string;
  titulo: string;
  tituloOriginal: string;
  autores: Author[];
  autoresString: string;
  generosString: string;
  urlLibro: string;
  entradasLibro: DatoEntrada[];
  portadaWidth: number;
  portadaHeight: number;
  notaMomoko: number;
  visitas: number;
  ordenSaga: number;
  tieneOpinion: boolean;
  fechaAlta: Date;
  filters: Filter[];
  score: number;
}
