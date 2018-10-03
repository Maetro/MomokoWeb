
import { environment } from 'environments/environment';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { catchError, map } from 'rxjs/operators';
import { GeneralDataResponse } from '../dtos/GeneralDataResponse';

@Injectable({
  providedIn: 'root'
})
export class GeneralDataService {

  private log = environment.log;

  private informacionGeneralUrl: string = environment.informacionGeneralUrl;

  resultados: Observable<GeneralDataResponse>;

  constructor(private http: HttpClient) { }

  getInformacionGeneral(): Observable<GeneralDataResponse> {
    if (this.log) {
      console.log('getInformacionGeneral()');
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });
    this.resultados = this.http.get(this.informacionGeneralUrl, { headers: headers }).pipe(
      map(this.extractData),
      catchError(error => Observable.throw(error || 'Server error')),);
    return this.resultados;

  }

  private extractData(res: GeneralDataResponse) {
    return res;
  }

}
