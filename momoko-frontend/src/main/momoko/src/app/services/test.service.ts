import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import { ObtenerLivetestResponse } from '../dtos/response/obtenerLivetestResponse';

@Injectable()
export class TestService {

  private log = environment.log;

  private testUrl = environment.testUrl;

  constructor(
    private http: HttpClient
  ) { }

  getTest(): Observable<ObtenerLivetestResponse> {
    console.log('test Redirect')
    return this.http.get<ObtenerLivetestResponse>(this.testUrl).map(this.extractTest);
  }

  private extractTest(res: ObtenerLivetestResponse) {
    return res;
  }


}
