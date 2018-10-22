import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Author } from 'app/dtos/autor';
import { Observable } from 'rxjs';
import { Cookie } from 'ng2-cookies';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  
  private log = environment.log;

  private serverUrl = environment.serverUrl;

  constructor(private http: HttpClient) {}

  getAuthors(): Observable<Author[]> {
    if (this.log) {
      console.log('Obteniendo Filtros');
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });
    return this.http
      .get<Author[]>(this.serverUrl + "model/author", { headers: headers });
  }

}
