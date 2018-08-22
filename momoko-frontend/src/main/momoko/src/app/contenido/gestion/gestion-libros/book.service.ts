import { Injectable } from '@angular/core';
import { Libro } from '../../../dtos/libro';
import { Cookie } from 'ng2-cookies';
import { environment } from 'environments/environment';
import { Observable, throwError } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private log = environment.log;

  private booksUrl = environment.booksUrl;
  private addBookUrl = environment.addBookUrl;
  private getBook = environment.getBook;

  constructor(private http: HttpClient) {}

  getBooks(): Observable<Libro[]> {
    if (this.log) {
      console.log('Obteniendo Libros');
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: Cookie.get('access_token')
    });

    return this.http.get<Libro[]>(this.booksUrl, { headers: headers }).pipe(
      map(this.extractBooks),
      catchError(error => throwError(error || 'Server error'))
    );
  }

  private extractBooks(res: Libro[]) {
    return res;
  }

  getBookByUrl(urlBook: string): Observable<Libro> {
    if (this.log) {
      console.log('Obteniendo Libro: ' + urlBook);
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: Cookie.get('access_token')
    });
    return this.http
      .get<Libro>(this.getBook + urlBook, { headers: headers })
      .pipe(
        map(this.extractBook),
        catchError(error => throwError(error || 'Server error'))
      );
  }

  private extractBook(res: Libro) {
    return res;
  }
}
