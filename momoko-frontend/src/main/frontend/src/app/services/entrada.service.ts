import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Entrada } from 'app/dtos/entrada';
import { HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { environment } from 'environments/environment';

@Injectable()
export class EntradaService {

  private entradasUrl = environment.entradasUrl;
  private addEntradaUrl = environment.addEntradaUrl;

  allEntradasList: Entrada[] = new Array();

  constructor(private http: HttpClient) { }

  getEntradas(): Promise<Entrada[]> {
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
        e.orden = json.orden;
        // console.log(g);
        this.allEntradasList.push(e);
      }

      return this.allEntradasList;

    });
  }

  guardarEntrada(entrada: Entrada): Promise<any> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
      });
    return this.http
      .post(this.addEntradaUrl, JSON.stringify(entrada), {headers: headers})
      .toPromise();
  }

}
