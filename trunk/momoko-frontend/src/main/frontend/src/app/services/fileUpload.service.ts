import { environment } from './../../environments/environment';
import { Injectable, OnInit } from '@angular/core';
import { Http, Response} from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';

import {Observable} from 'rxjs/Rx';

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class FileUploadService {
  private uploadUrl = environment.uploadUrl;  // URL to web api
  private urlFiles = environment.urlFiles;

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {}

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }


  fileChange(event): Observable<string> {
      const fileList: FileList = event.files;
      if (fileList.length > 0) {
        const file: File = fileList[0];
        const formData: FormData = new FormData();
          formData.append('uploadFile', file, file.name);
          const headers = new Headers();
          headers.append('Accept', 'application/json');
          const options = new RequestOptions({ headers: headers });
          return this.http.post(this.uploadUrl, formData, options)
            .map((res: Response) => this.urlFiles + file.name)
            .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
      } else{
        return null;
      }
  }



}
