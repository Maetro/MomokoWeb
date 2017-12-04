import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { IndexData } from 'app/dtos/indexData';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import { ObtenerIndexDataResponse } from 'app/dtos/response/obtenerIndexDataResponse';

@Injectable()
export class IndexDataService {

  private indexDataUrl = environment.indexDataUrl;

  resultados: Observable<ObtenerIndexDataResponse>;

  constructor(private http: HttpClient) { }

  getIndexData(): Observable<ObtenerIndexDataResponse> {
    console.log('getIndexData()');

    this.resultados = this.http.get(this.indexDataUrl)
    .map(this.extractIndexData)
    .catch(error => Observable.throw(error || 'Server error').share());
    return this.resultados;

  }

  private extractIndexData(res: Response) {
    return res;
  }

}
