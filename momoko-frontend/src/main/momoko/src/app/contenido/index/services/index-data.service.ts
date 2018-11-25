
import {throwError as observableThrowError,  Observable } from 'rxjs';

import {catchError, map} from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { IndexDataResponse } from '../dtos/indexDataResponse';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CustomBlock } from 'app/contenido/comunes/common-dtos/custom-block';
import { Cookie } from 'ng2-cookies';
import { Author } from 'app/dtos/autor';

@Injectable()
export class IndexDataService {
  

  private log = environment.log;
  private serverUrl = environment.serverUrl;

  private indexDataUrl = environment.indexDataUrl;

  resultados: Observable<IndexDataResponse>;

  constructor(private http: HttpClient) { }

  getIndexData(): Observable<IndexDataResponse> {
    if (this.log) {
      console.log('getIndexData()');
    }

    this.resultados = this.http.get(this.indexDataUrl).pipe(
      map(this.extractIndexData),
      catchError(error => observableThrowError(error || 'Server error')),);
    return this.resultados;

  }

  private extractIndexData(res: IndexDataResponse) {
    return res;
  }

  getCustomBlockIndex(): Observable<CustomBlock> {

      if (this.log) {
        console.log('Obteniendo Autor');
      }
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      return this.http
        .get<CustomBlock>(this.serverUrl + "public/customblock/INDEX/", { headers: headers });
    
  }

}
