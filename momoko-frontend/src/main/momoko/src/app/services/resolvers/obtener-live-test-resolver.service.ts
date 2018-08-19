import { throwError as observableThrowError, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import {
  Resolve,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { FichaLibro } from '../../dtos/fichaLibro';
import { environment } from '../../../environments/environment';
import { LibroService } from '../libro.service';
import { ObtenerLivetestResponse } from '../../dtos/response/obtenerLivetestResponse';
import { TestService } from '../test.service';
import { map, take, catchError } from 'rxjs/operators';

@Injectable()
export class ObtenerLiveTestResolverService
  implements Resolve<ObtenerLivetestResponse> {
  private log = environment.log;

  constructor(private testService: TestService, private router: Router) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<ObtenerLivetestResponse> {
    if (this.log) {
      console.log('Obteniendo test');
    }
    const url = route.paramMap.get('url');

    return this.testService.getTest().pipe(
      take(1),
      map(resultado => {
        if (resultado.resultado != null) {
          return resultado;
        } else {
          // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      }),
      catchError(this.handleError)
    );
  }

  private handleError(error: any) {
    let errMsg = error.message
      ? error.message
      : error.status
        ? `${error.status} - ${error.statusText}`
        : 'Server error';
    console.error(errMsg); // log to console instead
    return observableThrowError(errMsg);
  }
}
