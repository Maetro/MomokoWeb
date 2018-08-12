import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Entrada } from '../dtos/entrada';
import { HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs/Observable';
import { GuardarEntradaResponse } from '../dtos/response/guardarEntradaResponse';
import { ObtenerEntradaResponse } from '../dtos/response/obtenerEntradaResponse';
import { EntradaSimple } from '../dtos/entradaSimple';

@Injectable()
export class EntradaService {

  private log = environment.log;

  private entradasUrl = environment.entradasUrl;
  private addEntradaUrl = environment.addEntradaUrl;
  private getEntradaUrl = environment.getEntradaUrl;
  private getEntradaAdminUrl = environment.getEntradaAdminUrl;

  allEntradasList: Entrada[] = new Array();

  constructor(private http: HttpClient) { }

  getEntrada(urlEntrada): Observable<ObtenerEntradaResponse> {
    return this.http.get<ObtenerEntradaResponse>(this.getEntradaUrl + urlEntrada).map(this.obtenerEntradaDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getEntradaAdmin(urlEntrada): Observable<Entrada> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });
    return this.http.get<Entrada>(this.getEntradaAdminUrl + urlEntrada, { headers: headers })
      .map(this.obtenerEntrada)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerEntradaDeRespuesta(res: ObtenerEntradaResponse) {
    if (res.entrada != null) {
      res.entrada.contenidoEntrada = res.entrada.contenidoEntrada.replace(/\r\n|\n|\r/g, '<br />');
    }
    return res;
  }

  private obtenerEntrada(res: Entrada) {
    res.contenidoEntrada = res.contenidoEntrada.replace(/\r\n|\n|\r/g, '<br />');
    return res;
  }

  getAllEntradas(): Observable<EntradaSimple[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });
    if (this.log) {
      console.log(Cookie.get('access_token'));
    }
    return this.http.get<EntradaSimple[]>(this.entradasUrl, { headers: headers }).map(this.obtenerEntradasDeRespuesta)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private obtenerEntradasDeRespuesta(res: EntradaSimple[]) {
    return res;
  }

  guardarEntrada(entrada: Entrada): Observable<GuardarEntradaResponse> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });

    return this.http
      .post(this.addEntradaUrl, JSON.stringify(entrada), { headers: headers })
      .map(this.obtenerRespuestaGuardadoEntrada)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private obtenerRespuestaGuardadoEntrada(res: GuardarEntradaResponse) {
    if (res.entrada != null) {
      res.entrada.contenidoEntrada = res.entrada.contenidoEntrada.replace(/\r\n|\n|\r/g, '<br />');
    }
    return res;
  }
}
