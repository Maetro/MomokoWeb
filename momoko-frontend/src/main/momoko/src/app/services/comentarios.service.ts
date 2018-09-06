import { throwError as observableThrowError, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ComentarioRequest } from '../dtos/request/comentarioRequest';
import { GuardarComentarioResponse } from '../dtos/response/guardarComentarioResponse';
import { catchError, map } from 'rxjs/operators';

@Injectable()
export class ComentariosService {
  private log = environment.log;

  private addComentarioUrl = environment.addComentarioUrl;

  constructor(private http: HttpClient) {}

  guardarComentario(
    comentarioRequest: ComentarioRequest
  ): Observable<GuardarComentarioResponse> {
    return this.http
      .post<GuardarComentarioResponse>(this.addComentarioUrl, comentarioRequest);
  }
}
