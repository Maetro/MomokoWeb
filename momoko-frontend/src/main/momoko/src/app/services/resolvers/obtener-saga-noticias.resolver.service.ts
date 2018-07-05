import { Injectable } from '@angular/core';
import { ObtenerPaginaLibroNoticiasResponse } from '../../dtos/response/obtenerPaginaLibroNoticiasResponse';
import {
  Resolve,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { environment } from '../../../environments/environment';
import { ClasificadorService } from '../clasificador.service';
import { Observable } from 'rxjs/Observable';
import { ObtenerPaginaSagaNoticiasResponse } from '../../dtos/response/ObtenerPaginaSagaNoticiasResponse';

@Injectable()
export class ObtenerSagaNoticiasResolverService
  implements Resolve<ObtenerPaginaSagaNoticiasResponse> {
  private log = environment.log;

  constructor(
    private clasificadorService: ClasificadorService,
    private router: Router
  ) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<ObtenerPaginaSagaNoticiasResponse> {
    if (this.log) {
      console.log('Obteniendo lista noticias saga');
    }
    const url = route.paramMap.get('url-saga');
    const numeroPagina = route.paramMap.get('numero_pagina');
    if (numeroPagina) {
      return this.clasificadorService.getPaginaNoticiasSagaPage(url, numeroPagina).take(1)
        .map(sagaNoticias => {
          if (sagaNoticias.saga != null) {
            return sagaNoticias;
          } else {
            // url not found
            this.router.navigate(['/not-found']);
            return null;
          }
        });
    } else {
      return this.clasificadorService.getPaginaNoticiasSaga(url).take(1).map(sagaNoticias => {
          if (sagaNoticias.saga != null) {
            return sagaNoticias;
          } else {
            // url not found
            this.router.navigate(['/not-found']);
            return null;
          }
        });
    }
  }
}
