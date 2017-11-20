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
import { Observable } from 'rxjs/Observable';
import { FichaLibro } from 'app/dtos/fichaLibro';
import { GuardarLibroResponse } from 'app/dtos/response/guardarLibroResponse';

@Injectable()
export class LibroService {
  private librosUrl = environment.librosUrl;
  private addLibroUrl = environment.addLibroUrl;
  private generosUrl = environment.generosUrl;
  private addGeneroUrl = environment.addGeneroUrl;
  private obtenerLibroUrl = environment.obtenerLibroUrl;


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

  getLibro(urlLibro: string): Observable<FichaLibro> {
    return this.http.get<FichaLibro>(this.obtenerLibroUrl + urlLibro).map(this.extractLibro)
    .catch(error => Observable.throw(error || 'Server error'));
  }

  private extractLibro(res: FichaLibro) {
    return res;
  }

  guardarLibro(libro: Libro): Observable<GuardarLibroResponse> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
      });
    return this.http
      .post<GuardarLibroResponse>(this.addLibroUrl, JSON.stringify(libro), {headers: headers})
      .map(this.extractGuardarLibroResponse)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private extractGuardarLibroResponse(res: GuardarLibroResponse) {
    return res;
  }

  guardarGenero(genero: Genero): Promise<any> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
      });
    return this.http
      .post(this.addGeneroUrl, JSON.stringify(genero), { headers: headers })
      .toPromise();
  }

  getGeneros(): Observable<Genero[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
      });
    return this.http.get<Genero[]>(this.generosUrl, {headers: headers}).map(this.extractGeneros)
    .catch(error => Observable.throw(error || 'Server error'));
  }

  private extractGeneros(res: Genero[]) {
    return res;
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
