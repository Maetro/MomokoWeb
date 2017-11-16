import { Injectable } from '@angular/core';
import { ComentarioRequest } from 'app/dtos/request/comentarioRequest';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { GuardarComentarioResponse } from 'app/dtos/response/guardarComentarioResponse';
import { Cookie } from 'ng2-cookies';
import { environment } from 'environments/environment';

@Injectable()
export class ComentariosService {

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
