import { Editorial } from './dtos/editorial';
import { Injectable, OnInit } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient, HttpHeaders } from "@angular/common/http";

import 'rxjs/add/operator/toPromise';

import { Libro } from './dtos/libro';
import { Genero } from './dtos/genero';
import { Autor } from './dtos/autor';

import { environment } from './../environments/environment';

@Injectable()
export class LibroService {
  private librosUrl = environment.librosUrl;
  private addLibroUrl = environment.addLibroUrl;
  private generosUrl = environment.generosUrl;

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  results: string[];

  librosList: Libro[] = new Array();
  allGenerosList: Genero[] = new Array();

  constructor(private http: HttpClient) {}

  getLibros(): Promise<Libro[]> {


    this.http.get(this.librosUrl).toPromise().then((resp:Response) => {
      console.log("LLamada a la lista de libros");
      for(var numLibro in resp){
        var l = new Libro();
        let json = resp[numLibro];
        l.libroId = json.libroId;
        l.sagaId = json.sagaId;
        l.anoEdicion =  json.anoEdicion;
        l.citaLibro =  json.citaLibro;
        l.resumen = json.resumen;
        l.enlaceAmazon = json.enlaceAmazon;
        l.urlImagen = json.urlImagen;
        l.titulo = json.titulo;
        var editorial = new Editorial();
        editorial.editorialId = json.editorial.editorialId;
        editorial.nombreEditorial = json.editorial.nombreEditorial;
        l.editorial = editorial;
        var autoresList = new Array();
        for (var numAutor in json.autores){
          var a = new Autor();
          let jsonAutor = json.autores[numAutor];
          a.autorId = jsonAutor.autorId;
          a.nombre = jsonAutor.nombre;
          autoresList.push(a);
        }
        l.autores = autoresList;
        var generosList = new Array();
        for (var numGenero in json.generos){
          var genero = new Genero();
          let jsonGenero = json.generos[numAutor];
          genero.generoId = jsonGenero.generoId;
          genero.nombre = jsonGenero.nombre;
          generosList.push(genero);
          console.log(this.allGenerosList.indexOf(genero));
          if (this.allGenerosList.indexOf(genero) !== -1){
            this.allGenerosList.push(genero);
          }
        }
        l.generos = generosList;
        // console.log(l);
        this.librosList.push(l);
      }

      return this.librosList;

    });
    return Promise.resolve(this.librosList);
  }

  crearLibro(libro: Libro): Promise<Libro> {
    console.log(libro);
    console.log(JSON.stringify(libro));
    return this.http
      .post(this.addLibroUrl, JSON.stringify(libro), {headers: this.headers})
      .toPromise()
      .then(res => console.log(res))
      .catch(this.handleError);
  }

  getGeneros(): Promise<Genero[]> {
   return this.http.get(this.generosUrl).toPromise().then((resp:Response) => {

      for(var numGenero in resp){
        var g = new Genero();
        let json = resp[numGenero];
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
