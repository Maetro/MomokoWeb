import { Categoria } from "../../../dtos/categoria";

export interface GeneralDataResponse {
  nombresEditoriales: string[];
  nombresAutores: string[];
  titulosLibros: string[];
  nicksEditores: string[];
  categorias: Categoria[];
  sagas: string[];
}