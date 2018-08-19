
import {throwError as observableThrowError,  Observable } from 'rxjs';

import {catchError, map} from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GenrePageResponse } from '../dtos/genre/genrePageResponse';
import { ObtenerPaginaCategoriaResponse } from '../dtos/response/obtenerPaginaCategoriaResponse';
import { ObtenerPaginaEtiquetaResponse } from '../dtos/response/obtenerPaginaEtiquetaResponse';
import { ObtenerPaginaColeccionLibroResponse } from '../dtos/response/obtenerPaginaLibroNoticiasResponse';
import { ObtenerPaginaBusquedaResponse } from '../dtos/response/obtenerPaginaBusquedaResponse';
import { ObtenerPaginaRedactorResponse } from '../dtos/response/obtenerPaginaEditorResponse';
import { ObtenerPaginaEditorialResponse } from '../dtos/response/obtenerPaginaEditorialResponse';
import { ObtenerPaginaColeccionSagaResponse } from '../dtos/response/obtenerPaginaSagaColeccionResponse';
import { OrderType } from '../dtos/enums/ordertype';


@Injectable()
export class ClasificadorService {
 
  private log = environment.log;

  getGenreUrl = environment.getGenreUrl;
  getCategoriaUrl = environment.getCategoriaUrl;
  getEtiquetaUrl = environment.getEtiquetaUrl;
  getNoticiasLibroUrl = environment.getNoticiasLibroUrl;
  getMiscelaneosLibroUrl = environment.getMiscelaneosLibroUrl;
  getBusquedaUrl = environment.getBusquedaUrl;
  getEditorUrl = environment.getEditorUrl;
  getEditorialUrl = environment.getEditorialUrl;
  getNoticiasSagaUrl = environment.getNoticiasSagaUrl;
  getMiscelaneosSagaUrl = environment.getMiscelaneosSagaUrl;


  constructor(private http: HttpClient) { }

  getGenrePage(urlGenero, numeroPagina, order: OrderType): Observable<GenrePageResponse> {
    if (this.log) {
      console.log("SERVER: " + this.getGenreUrl + urlGenero + '/' + numeroPagina + "/" + order);
    }
    return this.http.get<GenrePageResponse>(this.getGenreUrl + urlGenero + '/' + numeroPagina + "/" + order).pipe(
      map(this.obtenerEntradaDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }


  private obtenerEntradaDeRespuesta(res: GenrePageResponse) {
    return res;
  }


  getCategoria(urlCategoria): Observable<ObtenerPaginaCategoriaResponse> {
    if (this.log) {
      console.log(urlCategoria);
    }
    return this.http.get<ObtenerPaginaCategoriaResponse>(this.getCategoriaUrl + urlCategoria).pipe(map(this.obtenergetCategoriaDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  getCategoriaPage(urlCategoria, numeroPagina): Observable<ObtenerPaginaCategoriaResponse> {
    if (this.log) {
      console.log(urlCategoria);
    }
    return this.http.get<ObtenerPaginaCategoriaResponse>(this.getCategoriaUrl + urlCategoria + '/' + numeroPagina).pipe(
      map(this.obtenergetCategoriaDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  getEtiqueta(urlEtiqueta): Observable<ObtenerPaginaEtiquetaResponse> {
    if (this.log) {
      console.log(urlEtiqueta);
    }
    return this.http.get<ObtenerPaginaEtiquetaResponse>(this.getEtiquetaUrl + urlEtiqueta).pipe(map(this.obtenerEtiquetaDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  getEtiquetaPage(urlEtiqueta, numeroPagina): Observable<ObtenerPaginaEtiquetaResponse> {
    if (this.log) {
      console.log(urlEtiqueta);
    }
    return this.http.get<ObtenerPaginaEtiquetaResponse>(this.getEtiquetaUrl + urlEtiqueta + '/' + numeroPagina).pipe(
      map(this.obtenerEtiquetaDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  
  private obtenerEtiquetaDeRespuesta(res: ObtenerPaginaEtiquetaResponse) {
    return res;
  }

  private obtenergetCategoriaDeRespuesta(res: ObtenerPaginaCategoriaResponse) {
    return res;
  }

  getPaginaNoticiasLibro(urlLibro): Observable<ObtenerPaginaColeccionLibroResponse> {
    if (this.log) {
      console.log(urlLibro);
    }
    return this.http.get<ObtenerPaginaColeccionLibroResponse>(this.getNoticiasLibroUrl + urlLibro).pipe(map(this.obtenerColeccionLibroDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  getPaginaNoticiasLibroPage(urlLibro, numeroPagina): Observable<ObtenerPaginaColeccionLibroResponse> {
    if (this.log) {
      console.log(urlLibro);
    }
    return this.http.get<ObtenerPaginaColeccionLibroResponse>(this.getNoticiasLibroUrl + urlLibro + '/' + numeroPagina).pipe(
      map(this.obtenerColeccionLibroDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  getPaginaMiscelaneosLibro(urlLibro): Observable<ObtenerPaginaColeccionLibroResponse> {
    if (this.log) {
      console.log(urlLibro);
    }
    return this.http.get<ObtenerPaginaColeccionLibroResponse>(this.getMiscelaneosLibroUrl + urlLibro).pipe(map(this.obtenerColeccionLibroDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  getPaginaMiscelaneosLibroPage(urlLibro, numeroPagina): Observable<ObtenerPaginaColeccionLibroResponse> {
    if (this.log) {
      console.log(urlLibro);
    }
    return this.http.get<ObtenerPaginaColeccionLibroResponse>(this.getMiscelaneosLibroUrl + urlLibro + '/' + numeroPagina).pipe(
      map(this.obtenerColeccionLibroDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  private obtenerColeccionLibroDeRespuesta(res: ObtenerPaginaColeccionLibroResponse) {
    return res;
  }

  getBusquedaPage(parametrosBusqueda: string): Observable<ObtenerPaginaBusquedaResponse> {
    if (this.log) {
      console.log(parametrosBusqueda);
    }
    return this.http.get<ObtenerPaginaColeccionLibroResponse>(this.getBusquedaUrl + parametrosBusqueda).pipe(
      map(this.obtenerPaginaBusquedaResponse),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  private obtenerPaginaBusquedaResponse(res: ObtenerPaginaBusquedaResponse) {
    return res;
  }

  getEditor(urlEditor): Observable<ObtenerPaginaRedactorResponse> {
    if (this.log) {
      console.log(urlEditor);
    }
    return this.http.get<ObtenerPaginaRedactorResponse>(this.getEditorUrl + urlEditor).pipe(map(this.obtenerEditorDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  getEditorPage(urlEditor, numeroPagina): Observable<ObtenerPaginaRedactorResponse> {
    if (this.log) {
      console.log(urlEditor);
    }
    return this.http.get<ObtenerPaginaRedactorResponse>(this.getEditorUrl + urlEditor + '/' + numeroPagina).pipe(
      map(this.obtenerEditorDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  private obtenerEditorDeRespuesta(res: ObtenerPaginaRedactorResponse) {
    return res;
  }

  getEditorial(urlEditorial): Observable<ObtenerPaginaEditorialResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaEditorialResponse>(this.getEditorialUrl + urlEditorial).pipe(map(this.obtenerEditorialDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  getEditorialPage(urlEditorial, numeroPagina): Observable<ObtenerPaginaEditorialResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaEditorialResponse>(this.getEditorialUrl + urlEditorial + '/' + numeroPagina).pipe(
      map(this.obtenerEditorialDeRespuesta),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  private obtenerEditorialDeRespuesta(res: ObtenerPaginaEditorialResponse) {
    return res;
  }

  getPaginaNoticiasSaga(urlEditorial): Observable<ObtenerPaginaColeccionSagaResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaColeccionSagaResponse>(this.getNoticiasSagaUrl + urlEditorial).pipe(map(this.obtenerObtenerPaginaSagaColeccionResponse),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  getPaginaNoticiasSagaPage(urlEditorial, numeroPagina): Observable<ObtenerPaginaColeccionSagaResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaColeccionSagaResponse>(this.getNoticiasSagaUrl + urlEditorial + '/' + numeroPagina).pipe(
      map(this.obtenerObtenerPaginaSagaColeccionResponse),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  getPaginaMiscelaneosSaga(urlEditorial): Observable<ObtenerPaginaColeccionSagaResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaColeccionSagaResponse>(this.getMiscelaneosSagaUrl + urlEditorial).pipe(map(this.obtenerObtenerPaginaSagaColeccionResponse),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  getPaginaMiscelaneosSagaPage(urlEditorial, numeroPagina): Observable<ObtenerPaginaColeccionSagaResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaColeccionSagaResponse>(this.getMiscelaneosSagaUrl + urlEditorial + '/' + numeroPagina).pipe(
      map(this.obtenerObtenerPaginaSagaColeccionResponse),
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')),);
  }

  private obtenerObtenerPaginaSagaColeccionResponse(res: ObtenerPaginaColeccionSagaResponse) {
    return res;
  }

}
