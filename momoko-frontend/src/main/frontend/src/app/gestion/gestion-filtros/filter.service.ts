import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Filter } from '../../dtos/filter/filter';
import { Cookie } from 'ng2-cookies';
import { SaveFilterResponse } from '../../dtos/filter/SaveFilterResponse';
import { SelectItem } from 'primeng/primeng';

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
      Authorization: Cookie.get('access_token')
    });
    return this.http
      .get<Filter[]>(this.filtersUrl, { headers: headers })
      .map(this.extractFilters)
      .catch(error => Observable.throw(error || 'Server error'));
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
      .post(this.addFilterUrl, JSON.stringify(filter), { headers: headers })
      .map(this.getSaveFilterResponse)
      .catch(error => Observable.throw(error || 'Server error'));
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
      .get<Filter>(this.filtersUrl + urlFilter, { headers: headers })
      .map(this.extractFilter)
      .catch(error => Observable.throw(error || 'Server error'));
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

  needToDefineCustomValues(filter: Filter): boolean {
    let result = true;
    if (filter.filterType === 'BOOLEAN') {
      result = false;
    }
    return result;
  }
}
