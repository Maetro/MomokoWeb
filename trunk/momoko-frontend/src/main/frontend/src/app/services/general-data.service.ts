
import { environment } from 'environments/environment';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Rx';
import { HttpClient } from '@angular/common/http';
import { GeneralDataResponse } from './interfaces/GeneralDataResponse';

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class GeneralDataService {

  private informacionGeneralUrl: string = environment.informacionGeneralUrl;

  resultados: Observable<GeneralDataResponse>;

  constructor(private http: HttpClient) { }


  getInformacionGeneral(): Observable<GeneralDataResponse> {
    console.log('getInformacionGeneral()');
    this.resultados = this.http.get(this.informacionGeneralUrl)
    .map(this.extractData)
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));

    return this.resultados;

  }

  private extractData(res: Response) {
    return res;
  }

}
