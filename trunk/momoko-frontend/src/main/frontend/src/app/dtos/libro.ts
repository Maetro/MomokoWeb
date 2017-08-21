import { Genero } from './genero';
import { Editorial } from './editorial';
import { Autor } from './autor';


export class Libro {
  libroId: number;
  sagaId: number;
  editorial: Editorial;
  generos: Genero[];
  anoEdicion: number;
  citaLibro: string;
  resumen: string;
  enlaceAmazon: string;
  urlImagen: string;
  titulo: string;
  autores: Autor[];
}
