import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import {
  Resolve,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { ObtenerPaginaBusquedaResponse } from '../../dtos/response/obtenerPaginaBusquedaResponse';
import { ClasificadorService } from '../clasificador.service';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';

@Injectable()
export class ObtenerListaBusquedaResolverService
  implements Resolve<ObtenerPaginaBusquedaResponse> {
  private log = environment.log;

  constructor(
    private clasificadorService: ClasificadorService,
    private router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<ObtenerPaginaBusquedaResponse> {
    if (this.log) {
      console.log('Obteniendo busqueda');
    }
    const parametrosABuscar = route.paramMap.get('parametros_a_buscar');

    return this.clasificadorService.getBusquedaPage(parametrosABuscar).pipe(
      take(1),
      map(busqueda => {
        if (busqueda.resultados != null) {
          return busqueda;
        } else {
          // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      })
    );
  }
}
