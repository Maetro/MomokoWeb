
import {throwError as observableThrowError, empty as observableEmpty,  Observable, throwError, EMPTY, of } from 'rxjs';

import {catchError, map} from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Entrada } from '../dtos/entrada';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';



import { ObtenerEntradaResponse } from '../dtos/response/obtenerEntradaResponse';
import { EntradaSimple } from '../dtos/entradaSimple';
import { GuardarEntradaResponse } from '../dtos/response/guardarEntradaResponse';
import { Cookie } from 'ng2-cookies';
import { Router } from '@angular/router';

@Injectable()
export class EntradaService {

  private log = environment.log;

  private entradasUrl = environment.entradasUrl;
  private addEntradaUrl = environment.addEntradaUrl;
  private getEntradaUrl = environment.getEntradaUrl;
  private getEntradaZonaUrl = environment.getEntradaZonaUrl;
  private getEntradaAdminUrl = environment.getEntradaAdminUrl;

  allEntradasList: Entrada[] = new Array();

  constructor(private http: HttpClient, private router: Router) { }

  getEntrada(urlEntrada): Observable<ObtenerEntradaResponse> {

    let url = this.getEntradaUrl + urlEntrada;
    return this.http.get<ObtenerEntradaResponse>(url).pipe(
      map(obtenerEntradaResponse => obtenerEntradaResponse),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    if (error.status === 404){
      return of(new ObtenerEntradaResponse);
    }
    return throwError(
      'Something bad happened; please try again later.');
  };

  getEntradaAdmin(urlEntrada): Observable<Entrada> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });
    return this.http.get<Entrada>(this.getEntradaAdminUrl + urlEntrada, { headers: headers }).pipe(
      map(this.obtenerEntrada),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  private obtenerEntradaDeRespuesta(res: ObtenerEntradaResponse) {
    if (res.entrada != null) {
      res.entrada.contenidoEntrada = res.entrada.contenidoEntrada.replace(/\r\n|\n|\r/g, '<br />');
    }
    return res;
  }

  private obtenerEntrada(res: Entrada) {
    res.contenidoEntrada = res.contenidoEntrada.replace(/\r\n|\n|\r/g, '<br />');
    return res;
  }

  getAllEntradas(): Observable<EntradaSimple[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });
    if (this.log) {
      console.log(Cookie.get('access_token'));
    }
    return this.http.get<EntradaSimple[]>(this.entradasUrl, { headers: headers }).pipe(map(this.obtenerEntradasDeRespuesta),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  private obtenerEntradasDeRespuesta(res: EntradaSimple[]) {
    return res;
  }

  guardarEntrada(entrada: Entrada): Observable<GuardarEntradaResponse> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });

    return this.http
      .post(this.addEntradaUrl, JSON.stringify(entrada), { headers: headers }).pipe(
      map(this.obtenerRespuestaGuardadoEntrada),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  private obtenerRespuestaGuardadoEntrada(res: GuardarEntradaResponse) {
    if (res.entrada != null) {
      res.entrada.contenidoEntrada = res.entrada.contenidoEntrada.replace(/\r\n|\n|\r/g, '<br />');
    }
    return res;
  }
}

