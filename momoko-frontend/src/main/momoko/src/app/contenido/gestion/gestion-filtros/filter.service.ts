
import {throwError as observableThrowError,  Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { SelectItem } from 'primeng/primeng';
import { environment } from '../../../../environments/environment';
import { Filter } from '../../../dtos/filter/filter';
import { SaveFilterResponse } from '../../../dtos/filter/SaveFilterResponse';
import { Globals } from '../../../app.globals';

@Injectable()
export class FilterService {

  private log = environment.log;

  private filtersUrl = environment.filtersUrl;
  private addFilterUrl = environment.addFilterUrl;
  private getFilter = environment.getFilter;

  constructor(private http: HttpClient) {}

  getFilters(): Observable<Filter[]> {
    if (this.log) {
      console.log('Obteniendo Filtros');
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });
    return this.http
      .get<Filter[]>(this.filtersUrl, { headers: headers }).pipe(
      map(this.extractFilters),
      catchError(error => observableThrowError(error || 'Server error'),));
  }

  private extractFilters(res: Filter[]) {
    return res;
  }

  saveFilter(filter: Filter): Observable<SaveFilterResponse> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });

    return this.http
      .post(this.addFilterUrl, JSON.stringify(filter), { headers: headers }).pipe(
      map(this.getSaveFilterResponse),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  private getSaveFilterResponse(res: SaveFilterResponse) {
    return res;
  }

  getFilterByUrl(urlFilter: string): Observable<Filter> {
    if (this.log) {
      console.log('Obteniendo Filtro: ' + urlFilter);
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: Cookie.get('access_token')
    });
    return this.http
      .get<Filter>(this.filtersUrl + urlFilter, { headers: headers }).pipe(
      map(this.extractFilter),
      catchError(error => observableThrowError(error || 'Server error')),);
  }

  private extractFilter(res: Filter) {
    return res;
  }

  public getStringOfFilterType(filterType: string): string {
    let result = '';
    if (filterType === 'BOOLEAN') {
      result = 'Verdadero o falso';
    } else if (filterType === 'BETWEEN') {
      result = 'Entre';
    } else if (filterType === 'GREATER_THAN') {
      result = 'Mayor que';
    } else if (filterType === 'LESS_THAN') {
      result = 'Menor que';
    } else if (filterType === 'EQUAL') {
      result = 'Igual a';
    } else if (filterType === 'CONTAINS') {
      result = 'Contiene';
    } else if (filterType === 'ALL_VALUES') {
      result = 'Todos los valores';
    } else if (filterType === 'ENUM') {
      result = 'Clasificador personalizado';
    } else {
      result = 'Otro';
    }

    return result;
  }

  public createReferenceValueOptions(): SelectItem[] {
    const refencedValueOptions = [];
    refencedValueOptions.push({ label: 'Definido en cada libro o saga', value: null });
    refencedValueOptions.push({ label: 'editorial', value: 'editorial', valueType: 'string'});
    refencedValueOptions.push({ label: 'Número de páginas', value: 'numeroPaginas', valueType: 'number' });
    refencedValueOptions.push({ label: 'Año publicacion', value: 'anoPublicacion', valueType: 'number' });
    refencedValueOptions.push({ label: 'Nota momoko', value: 'notaMomoko', valueType: 'number' });
    refencedValueOptions.push({ label: 'Visitas', value: 'visitas', valueType: 'number' });
    return refencedValueOptions;
  }

  changeFilterType(){

  }
}
