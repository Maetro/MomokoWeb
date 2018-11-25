import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Author } from 'app/dtos/autor';
import { environment } from 'environments/environment';
import { Cookie } from 'ng2-cookies';
import { Observable } from 'rxjs';
import { CustomBlock } from 'app/contenido/comunes/common-dtos/custom-block';
import { NewCustomBlockInfo } from '../dtos/new-custom-block-info';
import { SaveCustomBlockRequest } from '../dtos/save-custom-block-request';

@Injectable({
  providedIn: 'root'
})
export class CustomBlockService {
    


    private log = environment.log;

    private serverUrl = environment.serverUrl;
  
    constructor(private http: HttpClient) {}
  
    getCustomBlocks(): Observable<CustomBlock[]> {
      if (this.log) {
        console.log('Obteniendo Filtros');
      }
      const headers = new HttpHeaders({
        'Content-type': 'application/json',
        'Authorization': Cookie.get('access_token')
      });
      return this.http
        .get<CustomBlock[]>(this.serverUrl + "model/customBlock", { headers: headers });
    }

    savecustomBlock(savecustomBlockRequest: SaveCustomBlockRequest): any {
      
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': Cookie.get('access_token')
    });

    return this.http
      .post<Author>(this.serverUrl + 'model/customBlock/', JSON.stringify(savecustomBlockRequest), { headers: headers });
    }

    getNewCustomBlockInfoForForm(): Observable<NewCustomBlockInfo> {
      if (this.log) {
        console.log('Obteniendo Filtros');
      }
      const headers = new HttpHeaders({
        'Content-type': 'application/json',
        'Authorization': Cookie.get('access_token')
      });
      return this.http
        .get<NewCustomBlockInfo>(this.serverUrl + "model/customBlock/info", { headers: headers });
  }
  

}