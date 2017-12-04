import { Resolve, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { IndexData } from 'app/dtos/indexData';
import { IndexDataService } from 'app/services/index-data.service';

@Injectable()
export class ObtenerDatosGeneralesResolverService implements Resolve<IndexData> {

  constructor(private entradaService: IndexDataService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IndexData> {
    console.log('Obteniendo datos generales');

    return this.entradaService.getIndexData().take(1).map(indexData => {
      if (indexData) {
        return indexData;
      } else { // url not found
        this.router.navigate(['/']);
        return null;
      }
    });
  }

}
