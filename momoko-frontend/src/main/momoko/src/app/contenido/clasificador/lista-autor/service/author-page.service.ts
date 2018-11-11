import { Injectable } from "@angular/core";
import { environment } from "environments/environment";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { AuthorPageResponse } from "../dtos/author-page-response";
import { Cookie } from "ng2-cookies";

@Injectable({
    providedIn: "root"
  })
  export class AuthorPageService {
    
    serverUrl = environment.serverUrl;
  
    constructor(private http: HttpClient) {
    }
  
    getAuthorPage(authorUrl: string): Observable<AuthorPageResponse> {
  
        const headers = new HttpHeaders({
            'Content-type': 'application/json'
          });
  
      return this.http.get<AuthorPageResponse>(this.serverUrl + "public/author/" + authorUrl, { headers: headers });
    }
}