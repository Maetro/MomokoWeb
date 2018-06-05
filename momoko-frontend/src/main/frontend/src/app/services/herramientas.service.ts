import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';

import { Saga } from '../dtos/saga';
import { GuardarSagaResponse } from '../dtos/response/guardarSagaResponse';
import { EntradaUrl } from '../dtos/entradaurl';
import { environment } from 'environments/environment';

@Injectable()
export class HerramientasService {
  log = environment.log;
  private getEntradasUrl = environment.getEntradasUrl;

  constructor(private http: HttpClient) {}

  getUrlsEntradas(): Observable<EntradaUrl[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: 'Bearer ' + Cookie.get('access_token')
    });
    return this.http
      .get<EntradaUrl[]>(this.getEntradasUrl, { headers: headers })
      .map(this.extractUrlsEntradas)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private extractUrlsEntradas(res: EntradaUrl[]) {
    return res;
  }
}
