import { throwError as observableThrowError, Observable } from 'rxjs';
import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { ObtenerEntradaResponse } from '../dtos/response/obtenerEntradaResponse';
import { map, catchError } from 'rxjs/operators';

@Injectable()
export class SuscripcionService {
  private log = environment.log;

  private suscripcionUrl = environment.suscripcionUrl;

  constructor(private http: HttpClient) {}

  suscribirse(email): Observable<ObtenerEntradaResponse> {
    if (this.log) {
      console.log('suscribiendome');
    }
    return this.http
      .get<ObtenerEntradaResponse>(this.suscripcionUrl + email)
      .pipe(
        map(this.obtenerSuscripcionRespuesta),
        catchError((error: any) =>
          observableThrowError(error.json().error || 'Server error')
        )
      );
  }

  obtenerSuscripcionRespuesta(res: ObtenerEntradaResponse) {
    return res;
  }
}
