import { environment } from './../../environments/environment';
import { Injectable, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';

import { Observable } from 'rxjs/Rx';

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Cookie } from 'ng2-cookies';

@Injectable()
export class FileUploadService {
  private uploadUrl = environment.uploadUrl;  // URL to web api
  private urlFiles = environment.urlFiles;

  private headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }


  fileChange(event, tipoSubida): Observable<string> {
    const fileList: FileList = event.files;
    let num = 0;
    if (fileList.length > 0) {
      event.files.forEach(fichero => {
        if (num !== 0) {
          console.log('subiendo archivos')
          const formDataF: FormData = new FormData();
          formDataF.append('uploadFile', fichero, fichero.name);
          formDataF.append('tipoSubida', tipoSubida);
          const headersF = new Headers();
          headersF.append('Accept', 'application/json');
          headersF.append('Authorization', 'Bearer ' + Cookie.get('access_token'));
          const optionsF = new RequestOptions({ headers: headersF });
          this.http.post(this.uploadUrl, formDataF, optionsF)
            .map((res: Response) => this.urlFiles + tipoSubida + '/' + fichero.name)
            .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
        }
        num++;
      });

      console.log('subiendo archivos')
      const file: File = fileList[0];
      const formData: FormData = new FormData();
      formData.append('uploadFile', file, file.name);
      formData.append('tipoSubida', tipoSubida);
      const headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Authorization', 'Bearer ' + Cookie.get('access_token'));
      const options = new RequestOptions({ headers: headers });
      return this.http.post(this.uploadUrl, formData, options)
        .map((res: Response) => this.urlFiles + tipoSubida + '/' + file.name)
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    } else {
      return null;
    }
  }



}
