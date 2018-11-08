import { environment } from "environments/environment";
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { SuscribeContactRequest } from "./suscribe-contact-request";

@Injectable({
  providedIn: 'root'
})
export class SuscribeService {
  private log = environment.log;

  private serverUrl = environment.serverUrl;

  constructor(private http: HttpClient) {}

  sendEmailSuscribe(
    suscribeContactRequest: SuscribeContactRequest
  ): Observable<String> {
    if (this.log) {
      console.log('Enviando email');
    }
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    const options = { headers: headers, responseType: 'text' as 'text' };
    return this.http.post(
      this.serverUrl + 'public/sendEmailSuscribe',
      JSON.stringify(suscribeContactRequest),
      options
    );
  }
}
