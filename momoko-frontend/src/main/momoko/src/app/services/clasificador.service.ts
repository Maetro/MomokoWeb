import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import { ObtenerPaginaGeneroResponse } from '../dtos/response/obtenerPaginaGeneroResponse';
import { ObtenerPaginaCategoriaResponse } from '../dtos/response/obtenerPaginaCategoriaResponse';
import { ObtenerPaginaEtiquetaResponse } from '../dtos/response/obtenerPaginaEtiquetaResponse';
import { ObtenerPaginaLibroNoticiasResponse } from '../dtos/response/obtenerPaginaLibroNoticiasResponse';
import { ObtenerPaginaBusquedaResponse } from '../dtos/response/obtenerPaginaBusquedaResponse';


@Injectable()
export class ClasificadorService {
 
  private log = environment.log;

  getGeneroUrl = environment.getGeneroUrl;
  getCategoriaUrl = environment.getCategoriaUrl;
  getEtiquetaUrl = environment.getEtiquetaUrl;
  getNoticiasLibroUrl = environment.getNoticiasLibroUrl;
  getBusquedaUrl = environment.getBusquedaUrl;

  constructor(private http: HttpClient) { }

  getGenero(urlGenero): Observable<ObtenerPaginaGeneroResponse> {
    if (this.log) {
      console.log(urlGenero);
    }
    return this.http.get<ObtenerPaginaGeneroResponse>(this.getGeneroUrl + urlGenero).map(this.obtenerEntradaDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getGeneroPage(urlGenero, numeroPagina): Observable<ObtenerPaginaGeneroResponse> {
    if (this.log) {
      console.log(urlGenero);
    }
    return this.http.get<ObtenerPaginaGeneroResponse>(this.getGeneroUrl + urlGenero + '/' + numeroPagina)
      .map(this.obtenerEntradaDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerEntradaDeRespuesta(res: ObtenerPaginaGeneroResponse) {
    return res;
  }


  getCategoria(urlCategoria): Observable<ObtenerPaginaCategoriaResponse> {
    if (this.log) {
      console.log(urlCategoria);
    }
    return this.http.get<ObtenerPaginaCategoriaResponse>(this.getCategoriaUrl + urlCategoria).map(this.obtenergetCategoriaDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getCategoriaPage(urlCategoria, numeroPagina): Observable<ObtenerPaginaCategoriaResponse> {
    if (this.log) {
      console.log(urlCategoria);
    }
    return this.http.get<ObtenerPaginaCategoriaResponse>(this.getCategoriaUrl + urlCategoria + '/' + numeroPagina)
      .map(this.obtenergetCategoriaDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getEtiqueta(urlEtiqueta): Observable<ObtenerPaginaEtiquetaResponse> {
    if (this.log) {
      console.log(urlEtiqueta);
    }
    return this.http.get<ObtenerPaginaEtiquetaResponse>(this.getEtiquetaUrl + urlEtiqueta).map(this.obtenerEtiquetaDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getEtiquetaPage(urlEtiqueta, numeroPagina): Observable<ObtenerPaginaEtiquetaResponse> {
    if (this.log) {
      console.log(urlEtiqueta);
    }
    return this.http.get<ObtenerPaginaEtiquetaResponse>(this.getEtiquetaUrl + urlEtiqueta + '/' + numeroPagina)
      .map(this.obtenerEtiquetaDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  
  private obtenerEtiquetaDeRespuesta(res: ObtenerPaginaEtiquetaResponse) {
    return res;
  }

  private obtenergetCategoriaDeRespuesta(res: ObtenerPaginaCategoriaResponse) {
    return res;
  }

  getPaginaNoticiasLibro(urlLibro): Observable<ObtenerPaginaLibroNoticiasResponse> {
    if (this.log) {
      console.log(urlLibro);
    }
    return this.http.get<ObtenerPaginaLibroNoticiasResponse>(this.getNoticiasLibroUrl + urlLibro).map(this.obtenerNoticiasLibroDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getPaginaNoticiasLibroPage(urlLibro, numeroPagina): Observable<ObtenerPaginaLibroNoticiasResponse> {
    if (this.log) {
      console.log(urlLibro);
    }
    return this.http.get<ObtenerPaginaLibroNoticiasResponse>(this.getNoticiasLibroUrl + urlLibro + '/' + numeroPagina)
      .map(this.obtenerNoticiasLibroDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerNoticiasLibroDeRespuesta(res: ObtenerPaginaLibroNoticiasResponse) {
    return res;
  }

  getBusquedaPage(parametrosBusqueda: string): Observable<ObtenerPaginaBusquedaResponse> {
    if (this.log) {
      console.log(parametrosBusqueda);
    }
    return this.http.get<ObtenerPaginaLibroNoticiasResponse>(this.getBusquedaUrl + parametrosBusqueda)
      .map(this.obtenerNoticiasLibroDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }


}
