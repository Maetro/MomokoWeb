import { JsonAdapterService } from './../util/json-adapter.service';
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

  constructor(private http: HttpClient, private jsonAdapter: JsonAdapterService) { }

  getLibros(): Promise<Libro[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
      });
    this.librosList = new Array();
    return this.http.get(this.librosUrl, {headers: headers}).toPromise().then((resp: Response) => {
      for (const numLibro of Object.keys(resp)) {
        const l = this.jsonAdapter.adaptarLibro(resp[numLibro])
        this.librosList.push(l);
      }

      return this.librosList;

    });
  }

  guardarLibro(libro: Libro): Promise<any> {
    return this.http
      .post(this.addLibroUrl, JSON.stringify(libro), {
        headers: new HttpHeaders().set('Authorization', 'Bearer ' + Cookie.get('access_token'))
        .set('Content-type', 'application/json')})
      .toPromise();
  }

  guardarGenero(genero: Genero): Promise<any> {
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
