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
  
    sendEmailAuthor(authorContactRequest: AuthorContactRequest): Observable<boolean[]> {
      if (this.log) {
        console.log('Enviando email');
      }
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      return this.http
        .post<boolean[]>(this.serverUrl + "public/sendEmailAuthor", JSON.stringify(authorContactRequest), { headers: headers });
    }

    sendEmailEditor(editorContactRequest: EditorContactRequest): Observable<boolean[]> {
      if (this.log) {
        console.log('Enviando email');
      }
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      return this.http
        .post<boolean[]>(this.serverUrl + "public/sendEmailEditor", JSON.stringify(editorContactRequest), { headers: headers });
    }

    sendEmailPublisher(publisherContactRequest: PublisherContactRequest): Observable<boolean[]> {
      if (this.log) {
        console.log('Enviando email');
      }
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      return this.http
        .post<boolean[]>(this.serverUrl + "public/sendEmailPublisher", JSON.stringify(publisherContactRequest), { headers: headers });
    }

}