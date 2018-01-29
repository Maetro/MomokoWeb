import { Injectable } from '@angular/core';

import { environment } from '../../../environments/environment';
import { ObtenerIndexDataResponse } from '../../dtos/response/obtenerIndexDataResponse';
import { Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { IndexDataService } from '../index-data.service';
import { Observable } from 'rxjs';
import { Cookie } from 'ng2-cookies';

@Injectable()
export class ObtenerIndexDataResolverService implements Resolve<ObtenerIndexDataResponse> {

  private log = environment.log;

  constructor(private indexDataService: IndexDataService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerIndexDataResponse> {
    if (this.log) {
      console.log('Obteniendo index data');
      
    }

    return this.indexDataService.getIndexData().take(1).map(indexData => {
      if (indexData.librosMasVistos != null) {
        return indexData;
      } else { // url not found
        this.router.navigate(['/not-found']);
        return null;
      }
    });
  }

}
