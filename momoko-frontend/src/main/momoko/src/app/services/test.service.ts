import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ObtenerLivetestResponse } from '../dtos/response/obtenerLivetestResponse';
import { map, catchError } from 'rxjs/operators';

@Injectable()
export class TestService {
  private log = environment.log;

  private testUrl = environment.testUrl;

  constructor(private http: HttpClient) {}

  getTest(): Observable<ObtenerLivetestResponse> {
    console.log('test Redirect');
    return this.http
      .get<ObtenerLivetestResponse>(this.testUrl)
      .pipe(map(this.extractTest),);
  }

  private extractTest(res: ObtenerLivetestResponse) {
    return res;
  }
}
