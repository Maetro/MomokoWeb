import { Injectable } from '@angular/core';
import { Editorial } from '../../dtos/editorial';
import { Genero } from '../../dtos/genre/genero';
import { Libro } from '../../dtos/libro';
import { environment } from '../../../environments/environment';
import { Author } from 'app/dtos/autor';

@Injectable()
export class JsonAdapterService {

// Esto no es necesario, hay que eliminarlo

  private log = environment.log;

  constructor() { }

  adaptarLibro(json: any): Libro {
  const l = new Libro();
  l.libroId = json.libroId;
  l.anoEdicion = json.anoEdicion;
  l.citaLibro = json.citaLibro;
  l.resumen = json.resumen;
  l.enlaceAmazon = json.enlaceAmazon;
  l.urlImagen = json.urlImagen;
  l.titulo = json.titulo;
  l.anoPublicacion = json.anoPublicacion;
  l.tituloOriginal = json.tituloOriginal;
  l.numeroPaginas = json.numeroPaginas;
  const editorial = this.adaptarEditorial(json.editorial);
  l.editorial = editorial;
  const autoresList = new Array();
  let authors = '';
  for (const numAutor of Object.keys(json.autores)) {
    const a = this.adaptarAutor(json.autores[numAutor]);
    autoresList.push(a);
    authors = authors + a.name + ',';
  }
  l.autoresString = authors.slice(0, -1);
  l.autores = autoresList;
  const generosList = new Array();
  let generosString = '';
  for (const numGenero of Object.keys(json.generos)) {
    const genero = this.adaptarGenero(json.generos[numGenero]);
    generosList.push(genero);
    generosString = generosString + genero.nombre + ', ';
  }
  l.generosString = generosString.slice(0, -2);
  l.generos = generosList;
  l.urlLibro = json.urlLibro;
  return l;
  }

adaptarEditorial(json: any): Editorial {
  const editorial = new Editorial();
  editorial.editorialId = json.editorialId;
  editorial.nombreEditorial = json.nombreEditorial;
  return editorial;
}

  adaptarGenero(json: any): Genero {
    const genero = new Genero();
    genero.generoId = json.generoId;
    genero.nombre = json.nombre;
    return genero;
  }

  adaptarAutor(json: any): Author {
    const a = {} as Author;
    a.authorId = json.autorId;
    a.name = json.nombre;
    return a;
  }
}
