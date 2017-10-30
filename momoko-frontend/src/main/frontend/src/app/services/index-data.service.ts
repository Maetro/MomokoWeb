import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { IndexData } from 'app/dtos/indexData';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';

@Injectable()
export class IndexDataService {

  private indexDataUrl = environment.indexDataUrl;

  resultados: Observable<IndexData>;

  constructor(private http: HttpClient) { }

  getIndexData(): Observable<IndexData> {
    console.log('getIndexData()');

    this.resultados = this.http.get(this.indexDataUrl)
    .map(this.extractIndexData)
    .catch(error => Observable.throw(error || 'Server error'));
    return this.resultados;

  }

  private extractIndexData(res: Response) {
    return res;
  }

}
