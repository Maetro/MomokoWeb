import {map, take} from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { Resolve, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';

import { Observable } from 'rxjs';

import { environment } from 'environments/environment';
import { GeneralDataService } from './general-data.service';
import { GeneralDataResponse } from '../dtos/GeneralDataResponse';


@Injectable({
  providedIn: 'root'
})
export class GeneralDataResolverService implements Resolve<GeneralDataResponse> {

  private log = environment.log;

  constructor(private generalDataService: GeneralDataService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<GeneralDataResponse> {
    if (this.log) {
      console.log('Obteniendo general data');
    }

    return this.generalDataService.getInformacionGeneral().pipe(map(generalData => {
      if (generalData != null) {
        return generalData;
      } else { // url not found
        this.router.navigate(['/not-found']);
        return null;
      }
    }));
  }

}