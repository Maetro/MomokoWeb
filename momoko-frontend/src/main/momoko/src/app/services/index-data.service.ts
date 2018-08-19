
import {throwError as observableThrowError,  Observable } from 'rxjs';

import {catchError, map} from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { ObtenerIndexDataResponse } from '../dtos/response/obtenerIndexDataResponse';

import { HttpClient } from '@angular/common/http';



import { Cookie } from 'ng2-cookies';

@Injectable()
export class IndexDataService {

  private log = environment.log;

  private indexDataUrl = environment.indexDataUrl;

  resultados: Observable<ObtenerIndexDataResponse>;

  constructor(private http: HttpClient) { }

  getIndexData(): Observable<ObtenerIndexDataResponse> {
    if (this.log) {
      console.log('getIndexData()');
    }

    this.resultados = this.http.get(this.indexDataUrl).pipe(
      map(this.extractIndexData),
      catchError(error => observableThrowError(error || 'Server error')),);
    return this.resultados;

  }

  private extractIndexData(res: ObtenerIndexDataResponse) {
    return res;
  }

}
