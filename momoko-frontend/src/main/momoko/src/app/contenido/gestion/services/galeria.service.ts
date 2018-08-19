import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';

import { HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import { GuardarGaleriaResponse } from '../../../dtos/response/guardarGaleriaResponse';
import { JsonAdapterService } from '../../../services/util/json-adapter.service';
import { Galeria } from '../../../dtos/galeria';

@Injectable()
export class GaleriaService {

  private log = environment.log;

  addGaleriaUrl = environment.addGaleriaUrl;
  galeriasUrl = environment.galeriasUrl;


  constructor(private http: HttpClient, private jsonAdapter: JsonAdapterService) { }

  guardarGaleria(galeria: Galeria): Observable<GuardarGaleriaResponse> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
      });
    return this.http
      .post(this.addGaleriaUrl, JSON.stringify(galeria), { headers: headers })
      .map(this.extractGuardarGaleriaResponse)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private extractGuardarGaleriaResponse(res: GuardarGaleriaResponse) {
    return res;
  }

  getGalerias(): Observable<Galeria[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
      });
    return this.http.get<Galeria[]>(this.galeriasUrl, {headers: headers}).map(this.extractGalerias)
    .catch(error => Observable.throw(error || 'Server error'));
  }

  private extractGalerias(res: Galeria[]) {
    return res;
  }

}
