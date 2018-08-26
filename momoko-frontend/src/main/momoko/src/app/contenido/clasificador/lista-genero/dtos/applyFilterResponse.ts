import { Filter } from "../../../../dtos/filter/filter";
import { Libro } from "../../../../dtos/libro";
import { LibroSimple } from "../../../../dtos/libroSimple";

export class ApplyFilterResponse{
    
    avaliableFiltersList: Filter[];

    booksSelected: LibroSimple[];

}