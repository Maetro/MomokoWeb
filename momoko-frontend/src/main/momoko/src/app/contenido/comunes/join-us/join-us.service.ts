import { Injectable } from "@angular/core";
import { environment } from "environments/environment";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Cookie } from "ng2-cookies";
import { AuthorContactRequest, EditorContactRequest, PublisherContactRequest } from "./email-contact";

@Injectable({
    providedIn: 'root'
  })
  export class JoinUsService {
    
    private log = environment.log;
  
    private serverUrl = environment.serverUrl;
  
    constructor(private http: HttpClient) {}
  
    sendEmailAuthor(authorContactRequest: AuthorContactRequest): Observable<String> {
      if (this.log) {
        console.log('Enviando email');
      }
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      const options = { headers: headers, responseType: 'text' as 'text' };
      return this.http
        .post(this.serverUrl + "public/sendEmailAuthor", JSON.stringify(authorContactRequest), options);
    }

    sendEmailEditor(editorContactRequest: EditorContactRequest): Observable<String> {
      if (this.log) {
        console.log('Enviando email');
      }
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      const options = { headers: headers, responseType: 'text' as 'text' };
      return this.http
        .post(this.serverUrl + "public/sendEmailEditor", JSON.stringify(editorContactRequest), options);
    }

    sendEmailPublisher(publisherContactRequest: PublisherContactRequest): Observable<String> {
      if (this.log) {
        console.log('Enviando email');
      }
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      const options = { headers: headers, responseType: 'text' as 'text' };
      return this.http
        .post(this.serverUrl + "public/sendEmailPublisher", JSON.stringify(publisherContactRequest), options);
    }

}