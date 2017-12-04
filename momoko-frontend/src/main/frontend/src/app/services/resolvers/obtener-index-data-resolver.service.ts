
import { EntradaService } from 'app/services/entrada.service';
import { Injectable } from '@angular/core';
import { Resolve, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Entrada } from 'app/dtos/entrada';
import { Observable } from 'rxjs/Observable';
import { ObtenerEntradaResponse } from 'app/dtos/response/obtenerEntradaResponse';
import { ObtenerIndexDataResponse } from 'app/dtos/response/obtenerIndexDataResponse';
import { IndexDataService } from 'app/services/index-data.service';

@Injectable()
export class ObtenerIndexDataResolverService implements Resolve<ObtenerIndexDataResponse> {

  constructor(private indexDataService: IndexDataService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerIndexDataResponse> {
    console.log('Obteniendo index data');

    return this.indexDataService.getIndexData().take(1).map(indexData => {
      if (indexData) {
        return indexData;
      } else { // url not found
        this.router.navigate(['/']);
        return null;
      }
    });
  }

}
