
import { environment } from 'environments/environment';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Rx';
import { HttpClient } from '@angular/common/http';
import { GeneralDataResponse } from './interfaces/GeneralDataResponse';

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';

@Injectable()
export class GeneralDataService {

  private informacionGeneralUrl: string = environment.informacionGeneralUrl;

  resultados: Observable<GeneralDataResponse>;

  constructor(private http: HttpClient) { }


  getInformacionGeneral(): Observable<GeneralDataResponse> {
    console.log('getInformacionGeneral()');
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
      });
    this.resultados = this.http.get(this.informacionGeneralUrl, {headers: headers})
    .map(this.extractData)
    .catch(error => Observable.throw(error || 'Server error'));
    return this.resultados;

  }

  private extractData(res: Response) {
    return res;
  }

}
