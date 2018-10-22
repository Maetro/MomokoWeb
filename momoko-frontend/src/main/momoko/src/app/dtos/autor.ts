import { Libro } from "./libro";

export interface Author {
  authorId: number;
  name: string;
  authorUrl: string;
  birhtYear: number;
  deathYear: number;
  birthCountry: string;
  description: string;
  avatar: string;
  authorHeaderImage: string;
  authorBooks: Libro[];
}
