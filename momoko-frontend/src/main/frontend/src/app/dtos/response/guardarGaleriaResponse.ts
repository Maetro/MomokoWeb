import { Galeria } from 'app/dtos/galeria';

export class GuardarGaleriaResponse {
  estadoGuardado: string;
  galeriaDTO: Galeria;
  listaErroresValidacion: string[];
}
