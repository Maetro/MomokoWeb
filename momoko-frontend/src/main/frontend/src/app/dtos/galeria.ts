import { User } from 'app/dtos/user';

export class Galeria {
  galeriaId: number;
  autor: User;
  nombreGaleria: string;
  columnas: number;
  imagenes: string[];
  urlGaleria: string;
  fechaAlta: Date;
}
