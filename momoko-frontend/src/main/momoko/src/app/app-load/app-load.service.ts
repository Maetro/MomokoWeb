import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { InitData } from '../dtos/initData';
import { APP_DATA } from './app-data';



@Injectable()
export class AppLoadService {

  private initDataUrl = environment.initDataUrl;

  private log = environment.log;

  constructor(private httpClient: HttpClient) { }

  getDatosGenerales(): Promise<InitData> {
    if (this.log) {
      console.log(`getSettings:: before http.get call`);
    }

    const promise = this.httpClient.get<InitData>(this.initDataUrl)
      .toPromise<InitData>()
      .then(initData => {
        if (this.log) {
          console.log(`Settings from API: `, initData);
        }
        APP_DATA.menu = initData.menu;
        if (this.log) {
          console.log(`APP_DATA: `, APP_DATA);
        }
        return initData;
      });

    return promise;
  }
}