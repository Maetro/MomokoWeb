import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Editorial } from 'app/dtos/editorial';
import { Cookie } from 'ng2-cookies';
import { GuardarEditorialResponse } from 'app/dtos/response/guardarEditorialResponse';

@Injectable()
export class EditorialService {

  private log = environment.log;

  private editorialesUrl = environment.editorialesUrl;
  private addEditorialUrl = environment.addEditorialUrl;

  constructor(private http: HttpClient) { }

  getEditoriales(): Observable<Editorial[]> {
    if (this.log) {
      console.log('Obteniendo editoriales');
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.http.get<Editorial[]>(this.editorialesUrl, { headers: headers }).map(this.extractEditoriales)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private extractEditoriales(res: Editorial[]) {
    return res;
  }


  guardarEditorial(editorial: Editorial): Observable<Editorial> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });

    return this.http
      .post(this.addEditorialUrl, JSON.stringify(editorial), { headers: headers })
      .map(this.obtenerRespuestaGuardadoRedactor)
      .catch(error => Observable.throw(error || 'Server error'));
  }

  private obtenerRespuestaGuardadoRedactor(res: GuardarEditorialResponse) {
    return res;
  }

}
