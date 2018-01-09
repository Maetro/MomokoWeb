import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ComentarioRequest } from '../dtos/request/comentarioRequest';
import { Observable } from 'rxjs/Observable';
import { GuardarComentarioResponse } from '../dtos/response/guardarComentarioResponse';

@Injectable()
export class ComentariosService {

  private log = environment.log;

  private addComentarioUrl = environment.addComentarioUrl;

  constructor(private http: HttpClient) { }

  guardarComentario(comentarioRequest: ComentarioRequest): Observable<GuardarComentarioResponse> {

    return this.http
      .post<GuardarComentarioResponse>(this.addComentarioUrl, comentarioRequest)
      .map(this.extractGuardarComentarioResponse)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private extractGuardarComentarioResponse(res: GuardarComentarioResponse) {
    return res;
  }

}
