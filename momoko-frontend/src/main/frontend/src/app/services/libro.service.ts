import { Editorial } from './../dtos/editorial';
import { Injectable } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import 'rxjs/add/operator/toPromise';

import { Libro } from './../dtos/libro';
import { Genero } from './../dtos/genero';
import { Autor } from './../dtos/autor';

import { environment } from './../../environments/environment';
import { Cookie } from 'ng2-cookies';
import { RequestOptions } from '@angular/http';

@Injectable()
export class LibroService {
  private librosUrl = environment.librosUrl;
  private addLibroUrl = environment.addLibroUrl;
  private generosUrl = environment.generosUrl;
  private addGeneroUrl = environment.addGeneroUrl;

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  results: string[];

  librosList: Libro[] = new Array();
  allGenerosList: Genero[] = new Array();

  constructor(private http: HttpClient) { }

  getLibros(): Promise<Libro[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
      });
    console.log(Cookie.get('access_token'));
    this.librosList = new Array();
    return this.http.get(this.librosUrl, {headers: headers}).toPromise().then((resp: Response) => {
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
    console.log(Cookie.get('access_token'));
    return this.http
      .post(this.addLibroUrl, JSON.stringify(libro), {
        headers: new HttpHeaders().set('Authorization', 'Bearer ' + Cookie.get('access_token'))
        .set('Content-type', 'application/json')})
      .toPromise();
  }

  guardarGenero(genero: Genero): Promise<any> {
    console.log(genero);
    console.log(JSON.stringify(genero));
    return this.http
      .post(this.addGeneroUrl, JSON.stringify(genero), { headers: this.headers })
      .toPromise();
  }

  getGeneros(): Promise<Genero[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
      });
    return this.http.get(this.generosUrl, {headers: headers}).toPromise().then((resp: Response) => {
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