
import {throwError as observableThrowError,  Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ObtenerEntradaResponse } from '../dtos/response/obtenerEntradaResponse';

import { map, catchError } from 'rxjs/operators';

@Injectable()
export class VideoService {

  private log = environment.log;

  getVideoUrl = environment.obtenerVideoUrl;

  constructor(private http: HttpClient) { }

  getEntrada(urlEntrada): Observable<ObtenerEntradaResponse> {
    return this.http.get<ObtenerEntradaResponse>(this.getVideoUrl + urlEntrada).pipe(map(this.obtenerEntradaDeRespuesta),
    catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  private obtenerEntradaDeRespuesta(res: ObtenerEntradaResponse) {
    return res;
  }

}
