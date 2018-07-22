import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
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
    return this.http.get<GenrePageResponse>(this.getGenreUrl + urlGenero + '/' + numeroPagina + "/" + order)
      .map(this.obtenerEntradaDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }


  private obtenerEntradaDeRespuesta(res: GenrePageResponse) {
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

  getPaginaNoticiasLibro(urlLibro): Observable<ObtenerPaginaColeccionLibroResponse> {
    if (this.log) {
      console.log(urlLibro);
    }
    return this.http.get<ObtenerPaginaColeccionLibroResponse>(this.getNoticiasLibroUrl + urlLibro).map(this.obtenerColeccionLibroDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getPaginaNoticiasLibroPage(urlLibro, numeroPagina): Observable<ObtenerPaginaColeccionLibroResponse> {
    if (this.log) {
      console.log(urlLibro);
    }
    return this.http.get<ObtenerPaginaColeccionLibroResponse>(this.getNoticiasLibroUrl + urlLibro + '/' + numeroPagina)
      .map(this.obtenerColeccionLibroDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getPaginaMiscelaneosLibro(urlLibro): Observable<ObtenerPaginaColeccionLibroResponse> {
    if (this.log) {
      console.log(urlLibro);
    }
    return this.http.get<ObtenerPaginaColeccionLibroResponse>(this.getMiscelaneosLibroUrl + urlLibro).map(this.obtenerColeccionLibroDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getPaginaMiscelaneosLibroPage(urlLibro, numeroPagina): Observable<ObtenerPaginaColeccionLibroResponse> {
    if (this.log) {
      console.log(urlLibro);
    }
    return this.http.get<ObtenerPaginaColeccionLibroResponse>(this.getMiscelaneosLibroUrl + urlLibro + '/' + numeroPagina)
      .map(this.obtenerColeccionLibroDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerColeccionLibroDeRespuesta(res: ObtenerPaginaColeccionLibroResponse) {
    return res;
  }

  getBusquedaPage(parametrosBusqueda: string): Observable<ObtenerPaginaBusquedaResponse> {
    if (this.log) {
      console.log(parametrosBusqueda);
    }
    return this.http.get<ObtenerPaginaColeccionLibroResponse>(this.getBusquedaUrl + parametrosBusqueda)
      .map(this.obtenerColeccionLibroDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getEditor(urlEditor): Observable<ObtenerPaginaRedactorResponse> {
    if (this.log) {
      console.log(urlEditor);
    }
    return this.http.get<ObtenerPaginaRedactorResponse>(this.getEditorUrl + urlEditor).map(this.obtenerEditorDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getEditorPage(urlEditor, numeroPagina): Observable<ObtenerPaginaRedactorResponse> {
    if (this.log) {
      console.log(urlEditor);
    }
    return this.http.get<ObtenerPaginaRedactorResponse>(this.getEditorUrl + urlEditor + '/' + numeroPagina)
      .map(this.obtenerEditorDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerEditorDeRespuesta(res: ObtenerPaginaRedactorResponse) {
    return res;
  }

  getEditorial(urlEditorial): Observable<ObtenerPaginaEditorialResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaEditorialResponse>(this.getEditorialUrl + urlEditorial).map(this.obtenerEditorialDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getEditorialPage(urlEditorial, numeroPagina): Observable<ObtenerPaginaEditorialResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaEditorialResponse>(this.getEditorialUrl + urlEditorial + '/' + numeroPagina)
      .map(this.obtenerEditorialDeRespuesta)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerEditorialDeRespuesta(res: ObtenerPaginaEditorialResponse) {
    return res;
  }

  getPaginaNoticiasSaga(urlEditorial): Observable<ObtenerPaginaColeccionSagaResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaColeccionSagaResponse>(this.getNoticiasSagaUrl + urlEditorial).map(this.obtenerObtenerPaginaSagaColeccionResponse)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getPaginaNoticiasSagaPage(urlEditorial, numeroPagina): Observable<ObtenerPaginaColeccionSagaResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaColeccionSagaResponse>(this.getNoticiasSagaUrl + urlEditorial + '/' + numeroPagina)
      .map(this.obtenerObtenerPaginaSagaColeccionResponse)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getPaginaMiscelaneosSaga(urlEditorial): Observable<ObtenerPaginaColeccionSagaResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaColeccionSagaResponse>(this.getMiscelaneosSagaUrl + urlEditorial).map(this.obtenerObtenerPaginaSagaColeccionResponse)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getPaginaMiscelaneosSagaPage(urlEditorial, numeroPagina): Observable<ObtenerPaginaColeccionSagaResponse> {
    if (this.log) {
      console.log(urlEditorial);
    }
    return this.http.get<ObtenerPaginaColeccionSagaResponse>(this.getMiscelaneosSagaUrl + urlEditorial + '/' + numeroPagina)
      .map(this.obtenerObtenerPaginaSagaColeccionResponse)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  private obtenerObtenerPaginaSagaColeccionResponse(res: ObtenerPaginaColeccionSagaResponse) {
    return res;
  }

}
