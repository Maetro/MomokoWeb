import { Injectable } from '@angular/core';
import {
  Resolve,
  Router,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';

import { Observable } from 'rxjs';
import { ObtenerEntradaResponse } from '../../dtos/response/obtenerEntradaResponse';
import { environment } from '../../../environments/environment';
import { EntradaService } from '../entrada.service';
import { map, take } from 'rxjs/operators';

@Injectable()
export class ObtenerResenaResolverService
  implements Resolve<ObtenerEntradaResponse> {
  private log = environment.log;

  constructor(private entradaService: EntradaService, private router: Router) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<ObtenerEntradaResponse> {
    if (this.log) {
      console.log('Obteniendo opiniones 3');
    }
    const url_entrada = route.paramMap.get('url_entrada');

    return this.entradaService.getEntrada(url_entrada).pipe(
      take(1),
      map(entrada => {
        if (entrada.entrada != null) {
          return entrada;
        } else {
          // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      })
    );
  }
}
