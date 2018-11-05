import { Injectable } from "@angular/core";
import { environment } from "environments/environment";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Cookie } from "ng2-cookies";

@Injectable({
    providedIn: 'root'
  })
  export class JoinUsService {
    
    private log = environment.log;
  
    private serverUrl = environment.serverUrl;
  
    constructor(private http: HttpClient) {}
  
    sendEmail(email: any): Observable<boolean[]> {
      if (this.log) {
        console.log('Enviando email');
      }
      const headers = new HttpHeaders({
        'Content-type': 'application/json',
        'Authorization': Cookie.get('access_token')
      });
      return this.http
        .post<boolean[]>(this.serverUrl + "public/sendEmail", JSON.stringify(email), { headers: headers });
    }
}