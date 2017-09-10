import { Editorial } from './../dtos/editorial';
import { Injectable, OnInit } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import 'rxjs/add/operator/toPromise';

import { Libro } from './../dtos/libro';
import { Genero } from './../dtos/genero';
import { Autor } from './../dtos/autor';

import { environment } from './../../environments/environment';

@Injectable()
export class LibroService {
  private librosUrl = environment.librosUrl;
  private addLibroUrl = environment.addLibroUrl;
  private generosUrl = environment.generosUrl;

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  results: string[];

  librosList: Libro[] = new Array();
  allGenerosList: Genero[] = new Array();

  constructor(private http: HttpClient) { }

  getLibros(): Promise<Libro[]> {

    this.librosList = new Array();
    return this.http.get(this.librosUrl).toPromise().then((resp: Response) => {
      console.log('LLamada a la lista de libros');
      for (const numLibro of Object.keys(resp)) {
        const l = new Libro();
        const json = resp[numLibro];
        l.libroId = json.libroId;
        l.sagaId = json.sagaId;
        l.anoEdicion = json.anoEdicion;
        l.citaLibro = json.citaLibro;
        l.resumen = json.resumen;
        l.enlaceAmazon = json.enlaceAmazon;
        l.urlImagen = json.urlImagen;
        l.titulo = json.titulo;
        l.anoPublicacion = json.anoPublicacion;
        l.tituloOriginal = json.tituloOriginal;
        l.numeroPaginas = json.numeroPaginas;
        const editorial = new Editorial();
        editorial.editorialId = json.editorial.editorialId;
        editorial.nombreEditorial = json.editorial.nombreEditorial;
        l.editorial = editorial;
        const autoresList = new Array();
        let autoresString = '';
        for (const numAutor of Object.keys(json.autores)) {
          const a = new Autor();
          const jsonAutor = json.autores[numAutor];
          a.autorId = jsonAutor.autorId;
          a.nombre = jsonAutor.nombre;
          autoresList.push(a);
          autoresString = autoresString + a.nombre + ',';
        }
        l.autoresString = autoresString.slice(0, -1);
        l.autores = autoresList;
        const generosList = new Array();
        let generosString = '';
        for (const numGenero of Object.keys(json.generos)) {
          const genero = new Genero();
          const jsonGenero = json.generos[numGenero];
          genero.generoId = jsonGenero.generoId;
          genero.nombre = jsonGenero.nombre;
          generosList.push(genero);
          generosString = generosString + genero.nombre + ', ';
          if (this.allGenerosList.indexOf(genero) !== -1) {
            this.allGenerosList.push(genero);
          }
        }
        l.generosString = generosString.slice(0, -2);
        l.generos = generosList;
        console.log(l);
        this.librosList.push(l);
      }

      return this.librosList;

    });
  }

  guardarLibro(libro: Libro): Promise<any> {
    console.log(libro);
    console.log(JSON.stringify(libro));
    return this.http
      .post(this.addLibroUrl, JSON.stringify(libro), { headers: this.headers })
      .toPromise();
  }

  getGeneros(): Promise<Genero[]> {
    return this.http.get(this.generosUrl).toPromise().then((resp: Response) => {
      for (const numGenero of Object.keys(resp)) {
        const g = new Genero();
        const json = resp[numGenero];
        g.generoId = json.generoId;
        g.nombre = json.nombre;
        // console.log(g);
        this.allGenerosList.push(g);
      }

      return this.allGenerosList;

    });
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
