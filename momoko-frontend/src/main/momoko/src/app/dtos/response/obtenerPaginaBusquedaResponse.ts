import { ResultadoBusqueda } from "../resultadoBusqueda";

export class ObtenerPaginaBusquedaResponse {

    parametrosBusqueda: string;
    resultados: ResultadoBusqueda[];
    numeroEntradas: number;
  }