import { throwError as observableThrowError, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Cookie } from 'ng2-cookies';
import { RequestOptions } from '@angular/http';
import { Saga } from '../dtos/saga';
import { GuardarSagaResponse } from '../dtos/response/guardarSagaResponse';
import { FichaSaga } from '../dtos/fichaSaga';
import { map, catchError } from 'rxjs/operators';

@Injectable()
export class SagaService {
  private log = environment.log;

  private sagasUrl = environment.sagasUrl;
  private obtenerSagaAdmin = environment.obtenerSagaAdmin;
  private addSagaUrl = environment.addSagaUrl;
  private getSagaUrl = environment.getSagaUrl;

  constructor(private http: HttpClient) {}

  getSagas(): Observable<Saga[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: Cookie.get('access_token')
    });
    if (this.log) {
      console.log(Cookie.get('access_token'));
    }
    return this.http
      .get<Saga[]>(this.sagasUrl, { headers: headers }).pipe(
      map(this.obtenerSagasDeRespuesta),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  private obtenerSagasDeRespuesta(res: Saga[]) {
    return res;
  }

  getSagaAdmin(urlLibro: string): Observable<Saga> {
    return this.http
      .get<Saga>(this.obtenerSagaAdmin + urlLibro).pipe(
      map(this.extractSaga),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  getSaga(urlSaga: string): Observable<FichaSaga> {
    return this.http
      .get<FichaSaga>(this.getSagaUrl + urlSaga).pipe(
      map(this.extractFichaSaga),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  private extractSaga(res: Saga) {
    return res;
  }

  private extractFichaSaga(res: FichaSaga) {
    return res;
  }

  guardarSaga(saga: Saga): Observable<GuardarSagaResponse> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: Cookie.get('access_token')
    });

    return this.http
      .post(this.addSagaUrl, JSON.stringify(saga), { headers: headers }).pipe(
      map(this.obtenerRespuestaGuardadoSaga),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  private obtenerRespuestaGuardadoSaga(res: GuardarSagaResponse) {
    return res;
  }
}
