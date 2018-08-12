import { Genero } from './genero';
import { Editorial } from './editorial';
import { Autor } from './autor';
import { DatoEntrada } from './datoEntrada';


export class Libro {
  libroId: number;
  sagaId: number;
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
  autores: Autor[];
  autoresString: string;
  generosString: string;
  urlLibro: string;
  entradasLibro: DatoEntrada[];
  portadaWidth: number;
  portadaHeight: number;
  notaMomoko: number;
  visitas: number;
  ordenSaga: number;
}
