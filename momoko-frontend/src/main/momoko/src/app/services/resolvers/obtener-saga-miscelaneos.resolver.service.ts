import { Injectable } from '@angular/core';
import {
  Resolve,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { environment } from '../../../environments/environment';
import { ClasificadorService } from '../clasificador.service';
import { Observable } from 'rxjs/Observable';
import { ObtenerPaginaColeccionSagaResponse } from '../../dtos/response/obtenerPaginaSagaColeccionResponse';

@Injectable()
export class ObtenerSagaMiscelaneosResolverService
  implements Resolve<ObtenerPaginaColeccionSagaResponse> {
  private log = environment.log;

  constructor(
    private clasificadorService: ClasificadorService,
    private router: Router
  ) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<ObtenerPaginaColeccionSagaResponse> {
    if (this.log) {
      console.log('Obteniendo lista noticias saga');
    }
    const url = route.paramMap.get('url-saga');
    const numeroPagina = route.paramMap.get('numero_pagina');
    if (numeroPagina) {
      return this.clasificadorService.getPaginaMiscelaneosSagaPage(url, numeroPagina).take(1)
        .map(miscelaneosSaga => {
          if (miscelaneosSaga.saga != null) {
            return miscelaneosSaga;
          } else {
            // url not found
            this.router.navigate(['/not-found']);
            return null;
          }
        });
    } else {
      return this.clasificadorService.getPaginaMiscelaneosSaga(url).take(1).map(miscelaneosSaga => {
          if (miscelaneosSaga.saga != null) {
            return miscelaneosSaga;
          } else {
            // url not found
            this.router.navigate(['/not-found']);
            return null;
          }
        });
    }
  }
}