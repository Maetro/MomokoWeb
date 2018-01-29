import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { ObtenerIndexDataResponse } from '../dtos/response/obtenerIndexDataResponse';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/take';
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
    const _ga = Cookie.get('_ga');
    let url = this.indexDataUrl;
    // if (_ga != null){
    //   url = url +'/'+ _ga;
    // } 
    this.resultados = this.http.get(url)
      .map(this.extractIndexData)
      .catch(error => Observable.throw(error || 'Server error').share());
    return this.resultados;

  }

  private extractIndexData(res: Response) {
    return res;
  }

}
