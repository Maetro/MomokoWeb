import { Categoria } from 'app/dtos/categoria';

export class Genero {
  generoId: number;
  nombre: string;
  imagenCabeceraGenero: string;
  iconoGenero: string;
  urlGenero: string;
  categoria: Categoria;
}
