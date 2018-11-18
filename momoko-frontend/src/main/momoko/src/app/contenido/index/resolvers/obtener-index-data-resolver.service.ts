import { Injectable } from '@angular/core';

import { environment } from '../../../../environments/environment';
import { Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { IndexDataService } from '../services/index-data.service';
import { Observable } from 'rxjs';
import {map, take} from 'rxjs/operators';
import { IndexDataResponse } from 'app/contenido/index/dtos/indexDataResponse';

@Injectable()
export class ObtenerIndexDataResolverService implements Resolve<IndexDataResponse> {

  private log = environment.log;

  constructor(private indexDataService: IndexDataService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IndexDataResponse> {
    if (this.log) {
      console.log('Obteniendo index data');
      
    }

    return this.indexDataService.getIndexData().pipe(
      take(1),
      map(indexData => {
      if (indexData.librosMasVistos != null) {
        return indexData;
      } else { // url not found
        this.router.navigate(['/not-found']);
        return null;
      }
    }),);
  }

}
