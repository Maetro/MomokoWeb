import { Genero } from 'app/dtos/genero';

export class Menu {
  nombre: string;
  orden: number;
  url: string;
  generos: Genero[];
}
