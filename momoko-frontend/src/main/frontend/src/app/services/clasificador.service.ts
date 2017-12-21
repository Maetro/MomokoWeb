import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { ObtenerPaginaGeneroResponse } from 'app/dtos/response/obtenerPaginaGeneroResponse';
import { environment } from 'environments/environment';
import { ObtenerPaginaCategoriaResponse } from 'app/dtos/response/obtenerPaginaCategoriaResponse';
import { ObtenerPaginaLibroNoticiasResponse } from 'app/dtos/response/obtenerPaginaLibroNoticiasResponse';

@Injectable()
export class ClasificadorService {

  getGeneroUrl = environment.getGeneroUrl;
  getCategoriaUrl = environment.getCategoriaUrl;
  getNoticiasLibroUrl = environment.getNoticiasLibroUrl;

  constructor(private http: HttpClient) { }

  getGenero(urlGenero): Observable<ObtenerPaginaGeneroResponse> {
    console.log(urlGenero);
    return this.http.get<ObtenerPaginaGeneroResponse>(this.getGeneroUrl + urlGenero).map(this.obtenerEntradaDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerEntradaDeRespuesta(res: ObtenerPaginaGeneroResponse) {
    return res;
  }


  getCategoria(urlCategoria): Observable<ObtenerPaginaCategoriaResponse> {
    console.log(urlCategoria);
    return this.http.get<ObtenerPaginaCategoriaResponse>(this.getCategoriaUrl + urlCategoria).map(this.obtenergetCategoriaDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getCategoriaPage(urlCategoria, numeroPagina): Observable<ObtenerPaginaCategoriaResponse> {
    console.log(urlCategoria);
    return this.http.get<ObtenerPaginaCategoriaResponse>(this.getCategoriaUrl + urlCategoria + '/' + numeroPagina)
    .map(this.obtenergetCategoriaDeRespuesta)
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenergetCategoriaDeRespuesta(res: ObtenerPaginaCategoriaResponse) {
    return res;
  }

  getPaginaNoticiasLibro(urlLibro): Observable<ObtenerPaginaLibroNoticiasResponse> {
    console.log(urlLibro);
    return this.http.get<ObtenerPaginaLibroNoticiasResponse>(this.getNoticiasLibroUrl + urlLibro).map(this.obtenerNoticiasLibroDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getPaginaNoticiasLibroPage(urlLibro, numeroPagina): Observable<ObtenerPaginaLibroNoticiasResponse> {
    console.log(urlLibro);
    return this.http.get<ObtenerPaginaLibroNoticiasResponse>(this.getNoticiasLibroUrl + urlLibro + '/' + numeroPagina)
    .map(this.obtenerNoticiasLibroDeRespuesta)
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerNoticiasLibroDeRespuesta(res: ObtenerPaginaLibroNoticiasResponse) {
    return res;
  }

}
