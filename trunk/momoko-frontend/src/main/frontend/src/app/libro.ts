import { Genero } from './dtos/genero';
import { Editorial } from './dtos/editorial';
import { Autor } from './dtos/autor';


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
