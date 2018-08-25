
import { environment } from 'environments/environment';
import { Injectable, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';

import { Observable } from 'rxjs';

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Cookie } from 'ng2-cookies';
import { HttpHeaders } from '@angular/common/http';
import { UtilService } from '../../../services/util/util.service';

@Injectable()
export class FileUploadService {
  private log = environment.log;

  private uploadUrl = environment.uploadUrl; // URL to web api
  private urlFiles = environment.urlFiles;

  private headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http, private util: UtilService) {}

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
          if (this.log) {
            console.log('subiendo archivos');
          }
          const formDataF: FormData = new FormData();
          const partesF = fichero.name.split('.');
          const nombreF =
            this.util.convertToSlug(partesF[0]) + '.' + partesF[1];

          formDataF.append('uploadFile', fichero, nombreF);
          formDataF.append('tipoSubida', tipoSubida);
          const headersF = new Headers();
          headersF.append('Accept', 'application/json');
          headersF.append(
            'Authorization',
            Cookie.get('access_token')
          );
          const optionsF = new RequestOptions({ headers: headersF });
          this.http
            .post(this.uploadUrl, formDataF, optionsF)
            .map((res: Response) => this.urlFiles + tipoSubida + '/' + nombreF)
            .catch((error: any) =>
              Observable.throw(error.json().error || 'Server error')
            );
        }
        num++;
      });
      if (this.log) {
        console.log('subiendo archivos');
      }
      const file: File = fileList[0];
      const formData: FormData = new FormData();
      const partes = file.name.split('.');
      const nombre = this.util.convertToSlug(partes[0]) + '.' + partes[1];

      formData.append('uploadFile', file, nombre);
      formData.append('tipoSubida', tipoSubida);
      const headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Authorization', Cookie.get('access_token'));
      const options = new RequestOptions({ headers: headers });
      return this.http
        .post(this.uploadUrl, formData, options)
        .map((res: any) => {
          const finalImage = JSON.parse(res._body).response;
          console.log(res);
          return this.urlFiles + tipoSubida + '/' + finalImage;
        })
        .catch((error: any) =>
          Observable.throw(error.json().error || 'Server error')
        );
    } else {
      return null;
    }
  }
}
