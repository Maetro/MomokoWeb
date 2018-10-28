import { Author } from "app/dtos/autor";
import { LibroSimple } from "app/dtos/libroSimple";

export interface AuthorPageResponse{
    author: Author;
    authorBooks: LibroSimple[];
}