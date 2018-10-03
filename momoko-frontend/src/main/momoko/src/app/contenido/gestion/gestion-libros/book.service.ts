import { Injectable } from '@angular/core';
import { Libro } from '../../../dtos/libro';
import { Cookie } from 'ng2-cookies';
import { environment } from 'environments/environment';
import { Observable, throwError } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { UtilService } from '../../../services/util/util.service';
import { Filter } from '../../../dtos/filter/filter';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private log = environment.log;

  private serverUrl = environment.serverUrl;
  private booksUrl = environment.booksUrl;
  private addBookUrl = environment.addBookUrl;
  private getBook = environment.getBook;

  constructor(private http: HttpClient, private util: UtilService) {}

  getBooks(): Observable<Libro[]> {
    if (this.log) {
      console.log('Obteniendo Libros');
    }
    const headers = this.util.getHeaderWithAuth();
  
    return this.http.get<Libro[]>(this.booksUrl, { headers: headers });
  }

  getBookByUrl(urlBook: string): Observable<Libro> {
    if (this.log) {
      console.log('Obteniendo Libro: ' + urlBook);
    }

    const headers = this.util.getHeaderWithAuth();

    return this.http
      .get<Libro>(this.getBook + urlBook, { headers: headers });
  }

  getFiltersByGenres(urlGenres: string[]): Observable<Filter[]> {
    if (this.log) {
      console.log('getFiltersByGenres: ' + urlGenres);
    }
    const headers = this.util.getHeaderWithAuth();
    return this.http.post<Filter[]>(this.serverUrl + 'modelo/filter/genre/', JSON.stringify(urlGenres), { headers: headers });
  }

}
