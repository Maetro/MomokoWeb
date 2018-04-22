import { Injectable } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Cookie } from 'ng2-cookies';
import { RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Redactor } from 'app/dtos/redactor';
import { GuardarRedactorResponse } from 'app/dtos/response/guardarRedactorResponse';

@Injectable()
export class RedactorService {

  private log = environment.log;

  private redactoresUrl = environment.redactoresUrl;
  private addRedactorUrl = environment.addRedactorUrl;

  constructor(private http: HttpClient) { }

  getRedactores(): Observable<Redactor[]> {
    if (this.log) {
      console.log('Obteniendo redactores');
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.http.get<Redactor[]>(this.redactoresUrl, { headers: headers }).map(this.extractRedactores)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private extractRedactores(res: Redactor[]) {
    return res;
  }


  guardarRedactor(redactor: Redactor): Observable<GuardarRedactorResponse> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });

    return this.http
      .post(this.addRedactorUrl, JSON.stringify(redactor), { headers: headers })
      .map(this.obtenerRespuestaGuardadoRedactor)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private obtenerRespuestaGuardadoRedactor(res: GuardarRedactorResponse) {
    return res;
  }

}
