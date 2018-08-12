import { User } from './user';

export class Galeria {
  galeriaId: number;
  autor: User;
  nombreGaleria: string;
  columnas: number;
  imagenes: string[];
  urlGaleria: string;
  fechaAlta: Date;
}
