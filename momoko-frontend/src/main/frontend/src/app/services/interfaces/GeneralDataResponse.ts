import { Categoria } from 'app/dtos/categoria';

export interface GeneralDataResponse {
  nombresEditoriales: string[];
  nombresAutores: string[];
  titulosLibros: string[];
  categorias: Categoria[];
}
