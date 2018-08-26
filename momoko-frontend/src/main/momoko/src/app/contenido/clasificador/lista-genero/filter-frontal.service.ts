import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Filter } from '../../../dtos/filter/filter';
import { ApplyFilterResponse } from './dtos/applyFilterResponse';
import { Cookie } from 'ng2-cookies';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FilterFrontalService {

  private log = environment.log;

  private applyFiltersUrl = environment.applyFiltersUrl;

  constructor(private http: HttpClient) {}

  applyFilters(genreUrl: string, filter: Filter[]): Observable<ApplyFilterResponse> {
    if (this.log) {
      console.log('aplicando filtros');
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: Cookie.get('access_token')
    });
    return this.http
      .post<ApplyFilterResponse>(this.applyFiltersUrl + genreUrl, JSON.stringify(filter), { headers: headers }).pipe(
      map(this.extractFilters),
      catchError(error => throwError(error || 'Server error'),));
  }

  private extractFilters(res: ApplyFilterResponse) {
    return res;
  }

}
