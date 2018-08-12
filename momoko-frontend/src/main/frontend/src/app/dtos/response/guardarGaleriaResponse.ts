import { Galeria } from '../galeria';

export class GuardarGaleriaResponse {
  estadoGuardado: string;
  galeriaDTO: Galeria;
  listaErroresValidacion: string[];
}
