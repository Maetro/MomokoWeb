import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Author } from 'app/dtos/autor';
import { environment } from 'environments/environment';
import { Cookie } from 'ng2-cookies';
import { Observable } from 'rxjs';

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

  getAuthorByUrl(url: string): Observable<Author>{
    if (this.log) {
      console.log('Obteniendo Autor');
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });
    return this.http
      .get<Author>(this.serverUrl + "model/author/" + url, { headers: headers });
  }

  getAuthorById(id: number): Observable<Author>{
    if (this.log) {
      console.log('Obteniendo Autor');
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });
    return this.http
      .get<Author>(this.serverUrl + "model/author/" + id, { headers: headers });
  }

  saveAuthor(author: Author): Observable<Author> {

    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });

    return this.http
      .post<Author>(this.serverUrl + 'model/author/', JSON.stringify(author), { headers: headers });
  }

}
