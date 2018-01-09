import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { ObtenerEntradaResponse } from '../dtos/response/obtenerEntradaResponse';



@Injectable()
export class VideoService {

  private log = environment.log;

  getVideoUrl = environment.obtenerVideoUrl;

  constructor(private http: HttpClient) { }

  getEntrada(urlEntrada): Observable<ObtenerEntradaResponse> {
    return this.http.get<ObtenerEntradaResponse>(this.getVideoUrl + urlEntrada).map(this.obtenerEntradaDeRespuesta)
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerEntradaDeRespuesta(res: ObtenerEntradaResponse) {
    return res;
  }

}
