import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { Entrada } from '../../../dtos/entrada';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { GuardarEntradaResponse } from '../../../dtos/response/guardarEntradaResponse';
import { UtilService } from '../../../services/util/util.service';
import { SaveEntryRequest } from './dtos/save-entry-request';

@Injectable({
  providedIn: 'root'
})
export class EntryService {

  private log = environment.log;
  private serverUrl = environment.serverUrl;
  private addEntradaUrl = environment.addEntradaUrl;
  
  constructor(private http: HttpClient, private util: UtilService) {}

  getEntries(): Observable<EntradaSimple[]> {
    if (this.log) {
      console.log('Obteniendo Libros');
    }
    const headers = this.util.getHeaderWithAuth();
  
    return this.http.get<EntradaSimple[]>(this.serverUrl + 'modelo/entry', { headers: headers });
  }

  getEntradaAdmin(urlEntrada): Observable<Entrada> {
    
    if (this.log) {
      console.log('getEntradaAdmin');
    }

    const headers = this.util.getHeaderWithAuth();

    return this.http.get<Entrada>(this.serverUrl + 'modelo/entry/'+ urlEntrada, { headers: headers });
  }

  saveEntry(entrada: SaveEntryRequest): Observable<GuardarEntradaResponse> {

    const headers = this.util.getHeaderWithAuth();

    return this.http
      .post<GuardarEntradaResponse>(this.addEntradaUrl, JSON.stringify(entrada), { headers: headers });
  }

}
