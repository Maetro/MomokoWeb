import { Filter } from './filter';

export class SaveFilterResponse {
  listaErroresValidacion: string[];
  estadoGuardado: string;
  exception: any;
  filterDTO: Filter;
}
