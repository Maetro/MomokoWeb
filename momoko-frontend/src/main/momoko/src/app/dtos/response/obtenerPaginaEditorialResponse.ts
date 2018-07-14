import { Editorial } from '../editorial';
import { Genero } from "../genre/genero";
import { EntradaSimple } from "../entradaSimple";
import { LibroSimple } from "../libroSimple";



export class ObtenerPaginaEditorialResponse {

    editorial: Editorial;
    tresUltimasEntradasEditorial: EntradaSimple[];
    nueveLibrosEditorial: LibroSimple[];
    numeroLibros: number;
}
