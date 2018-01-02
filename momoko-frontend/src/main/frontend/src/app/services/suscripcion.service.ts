import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { ObtenerEntradaResponse } from 'app/dtos/response/obtenerEntradaResponse';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';

@Injectable()
export class SuscripcionService {

  private log = environment.log;

  private suscripcionUrl = environment.suscripcionUrl;

  constructor(private http: HttpClient) { }

  suscribirse(email): Observable<ObtenerEntradaResponse> {
    if (this.log) {
      console.log('suscribiendome');
    }
    return this.http.get<ObtenerEntradaResponse>(this.suscripcionUrl + email).map(this.obtenerSuscripcionRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  obtenerSuscripcionRespuesta(res: ObtenerEntradaResponse) {
    return res;
  }

}
