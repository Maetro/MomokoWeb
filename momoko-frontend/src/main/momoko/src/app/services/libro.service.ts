import { throwError as observableThrowError, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Libro } from '../dtos/libro';
import { Genero } from '../dtos/genre/genero';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { FichaLibro } from '../dtos/fichaLibro';
import { GuardarLibroResponse } from '../dtos/response/guardarLibroResponse';
import { GuardarGeneroResponse } from '../dtos/genre/guardarGeneroResponse';
import { JsonAdapterService } from './util/json-adapter.service';
import { catchError, map } from 'rxjs/operators';

@Injectable()
export class LibroService {
  private log = environment.log;

  private serverUrl = environment.serverUrl;
  private addBookUrl = environment.addBookUrl;
  private generosUrl = environment.generosUrl;
  private addGeneroUrl = environment.addGeneroUrl;
  private obtenerLibroUrl = environment.obtenerLibroUrl;

  results: string[];

  librosList: Libro[] = new Array();
  allGenerosList: Genero[] = new Array();

  constructor(
    private http: HttpClient,
    private jsonAdapter: JsonAdapterService
  ) {}

  getLibros(): Observable<Libro[]> {
      if (this.log) {
        console.log('Obteniendo Filtros');
      }
      const headers = new HttpHeaders({
        'Content-type': 'application/json',
        'Authorization': Cookie.get('access_token')
      });
      return this.http
        .get<Libro[]>(this.serverUrl + "modelo/libros", { headers: headers });
  }

  getLibro(urlLibro: string): Observable<FichaLibro> {
    return this.http
      .get<FichaLibro>(this.obtenerLibroUrl + urlLibro).pipe(
      map(this.extractLibro),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  private extractLibro(res: FichaLibro) {
    return res;
  }

  guardarLibro(libro: Libro): Observable<GuardarLibroResponse> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: Cookie.get('access_token')
    });
    return this.http
      .post<GuardarLibroResponse>(this.addBookUrl, JSON.stringify(libro), {
        headers: headers
      }).pipe(
      map(this.extractGuardarLibroResponse),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  private extractGuardarLibroResponse(res: GuardarLibroResponse) {
    return res;
  }

  guardarGenero(genero: Genero): Observable<GuardarGeneroResponse> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: Cookie.get('access_token')
    });
    return this.http
      .post(this.addGeneroUrl, JSON.stringify(genero), { headers: headers }).pipe(
      map(this.extractGuardarGeneroResponse),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  private extractGuardarGeneroResponse(res: GuardarGeneroResponse) {
    return res;
  }

  getGeneros(): Observable<Genero[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: Cookie.get('access_token')
    });
    return this.http.get<Genero[]>(this.generosUrl, { headers: headers }).pipe(
      map(this.extractGeneros),
      catchError(error => observableThrowError(error || 'Server error'))
    );
  }

  private extractGeneros(res: Genero[]) {
    return res;
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
