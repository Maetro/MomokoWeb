import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Entrada } from 'app/dtos/entrada';
import { HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs/Observable';
import { GuardarEntradaResponse } from 'app/dtos/response/guardarEntradaResponse';
import { ObtenerEntradaResponse } from 'app/dtos/response/obtenerEntradaResponse';

@Injectable()
export class EntradaService {

  private entradasUrl = environment.entradasUrl;
  private addEntradaUrl = environment.addEntradaUrl;
  private getEntradaUrl = environment.getEntradaUrl;

  allEntradasList: Entrada[] = new Array();

  constructor(private http: HttpClient) { }

  getEntrada(urlEntrada): Observable<ObtenerEntradaResponse> {
    return this.http.get<ObtenerEntradaResponse>(this.getEntradaUrl + urlEntrada).map(this.obtenerEntradaDeRespuesta)
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerEntradaDeRespuesta(res: ObtenerEntradaResponse) {
    return res;
  }

  getAllEntradas(): Observable<Entrada[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.http.get<Entrada[]>(this.entradasUrl, {headers: headers}).map(this.obtenerEntradasDeRespuesta)
    .catch(error => Observable.throw(error || 'Server error'));
  }

  private obtenerEntradasDeRespuesta(res: Entrada[]) {
    return res;
  }

  guardarEntrada(entrada: Entrada): Observable<GuardarEntradaResponse> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });

    return this.http
      .post(this.addEntradaUrl, JSON.stringify(entrada), { headers: headers })
      .map(this.obtenerRespuestaGuardadoEntrada)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private obtenerRespuestaGuardadoEntrada(res: GuardarEntradaResponse) {
    return res;
  }
}
