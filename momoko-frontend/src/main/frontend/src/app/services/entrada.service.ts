import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Entrada } from 'app/dtos/entrada';
import { HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs/Observable';
import { FichaEntrada } from 'app/dtos/fichaEntrada';

@Injectable()
export class EntradaService {

  private entradasUrl = environment.entradasUrl;
  private addEntradaUrl = environment.addEntradaUrl;
  private getEntradaUrl = environment.getEntradaUrl;

  allEntradasList: Entrada[] = new Array();

  constructor(private http: HttpClient) { }

  getEntrada(urlEntrada): Observable<FichaEntrada> {
    return this.http.get<FichaEntrada>(this.getEntradaUrl + urlEntrada).map(this.obtenerEntradaDeRespuesta)
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerEntradaDeRespuesta(res: FichaEntrada) {
    return res;
  }

  getEntradas(): Promise<Entrada[]> {
    this.allEntradasList = [];
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.http.get(this.entradasUrl, {headers: headers}).toPromise().then((resp: Response) => {
      for (const numGenero of Object.keys(resp)) {
        const e = new Entrada();
        const json = resp[numGenero];
        e.entradaId = json.enradaId;
        e.autor = json.autor;
        e.urlEntrada = json.urlEntrada;
        e.tipoEntrada = json.tipoEntrada;
        e.tituloEntrada = json.tituloEntrada;
        e.contenidoEntrada = json.contenidoEntrada;
        e.resumenEntrada = json.resumenEntrada;
        e.estadoEntrada = json.estadoEntrada;
        e.permitirComentarios = json.permitirComentarios;
        e.padreEntrada = json.padreEntrada;
        e.libroEntrada = json.libroEntrada;
        e.numeroComentarios = json.numeroComentarios;
        e.etiquetas = json.etiquetas;
        e.orden = json.orden;
        // console.log(g);
        this.allEntradasList.push(e);
      }

      return this.allEntradasList;

    });
  }

  getAllEntradas(): Observable<Entrada[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.http.get<Entrada[]>(this.entradasUrl, {headers: headers}).map(this.obtenerEntradasDeRespuesta)
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerEntradasDeRespuesta(res: Entrada[]) {
    return res;
  }

  guardarEntrada(entrada: Entrada): Promise<any> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });

    console.log(JSON.stringify(entrada));
    return this.http
      .post(this.addEntradaUrl, JSON.stringify(entrada), { headers: headers })
      .toPromise();
  }

}
