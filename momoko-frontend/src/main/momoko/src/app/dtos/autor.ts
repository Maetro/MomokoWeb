import { Libro } from "./libro";
import { SocialData } from "./abstract/socialData";

export interface Author extends SocialData{
  authorId: number;
  name: string;
  authorUrl: string;
  birhtYear: number;
  deathYear: string;
  birthCountry: string;
  description: string;
  avatar: string;
  authorHeaderImage: string;
  authorBooks: Libro[];
}
