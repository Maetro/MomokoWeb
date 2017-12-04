import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/toPromise';

import { environment } from 'environments/environment';
import { IndexData } from 'app/dtos/indexData';
import { APP_DATA } from 'app/app-load/app-data';
import { InitData } from 'app/dtos/initData';

@Injectable()
export class AppLoadService {

  private initDataUrl = environment.initDataUrl;

  constructor(private httpClient: HttpClient) { }

  getDatosGenerales(): Promise<InitData> {
    console.log(`getSettings:: before http.get call`);

    const promise = this.httpClient.get<InitData>(this.initDataUrl)
      .toPromise<InitData>()
      .then(initData => {
        console.log(`Settings from API: `, initData);

        APP_DATA.menu = initData.menu;

        console.log(`APP_DATA: `, APP_DATA);

        return initData;
      });

    return promise;
  }
}
