import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'environments/environment';
import { Cookie } from 'ng2-cookies';
import { RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Saga } from '../dtos/saga';
import { GuardarSagaResponse } from '../dtos/response/guardarSagaResponse';

@Injectable()
export class SagaService {

  private log = environment.log;

  private sagasUrl = environment.sagasUrl;
  private obtenerSagaAdmin = environment.obtenerSagaAdmin;
  private addSagaUrl = environment.addSagaUrl;
  constructor(
    private http: HttpClient
  ) { }

  getSagas(): Observable<Saga[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });
    if (this.log) {
      console.log(Cookie.get('access_token'));
    }
    return this.http.get<Saga[]>(this.sagasUrl, { headers: headers }).map(this.obtenerSagasDeRespuesta)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private obtenerSagasDeRespuesta(res: Saga[]) {
    return res;
  }

  getSaga(urlLibro: string): Observable<Saga> {
    return this.http.get<Saga>(this.obtenerSagaAdmin + urlLibro).map(this.extractSaga)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private extractSaga(res: Saga) {
    return res;
  }

  guardarSaga(saga: Saga): Observable<GuardarSagaResponse> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });

    return this.http
      .post(this.addSagaUrl, JSON.stringify(saga), { headers: headers })
      .map(this.obtenerRespuestaGuardadoSaga)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private obtenerRespuestaGuardadoSaga(res: GuardarSagaResponse) {
    return res;
  }


}
