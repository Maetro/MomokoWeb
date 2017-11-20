import { environment } from 'environments/environment';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { FichaEntrada } from 'app/dtos/fichaEntrada';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class VideoService {

  getVideoUrl = environment.obtenerVideoUrl;

  constructor(private http: HttpClient) { }

  getEntrada(urlEntrada): Observable<FichaEntrada> {
    return this.http.get<FichaEntrada>(this.getVideoUrl + urlEntrada).map(this.obtenerEntradaDeRespuesta)
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerEntradaDeRespuesta(res: FichaEntrada) {
    return res;
  }

}
