import { Injectable } from '@angular/core';

import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment';
import { ClasificadorService } from '../clasificador.service';
import { ObtenerPaginaCategoriaResponse } from '../../dtos/response/obtenerPaginaCategoriaResponse';
import { ObtenerPaginaEditorialResponse } from '../../dtos/response/obtenerPaginaEditorialResponse';


@Injectable()
export class ObtenerListaEditoralResolverService implements Resolve<ObtenerPaginaEditorialResponse> {

  private log = environment.log;

  constructor(private clasificadorService: ClasificadorService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerPaginaEditorialResponse> {
    if (this.log) {
      console.log('Obteniendo lista Editorial');
    }
    const url = route.paramMap.get('url_editorial');
    const numeroPagina = route.paramMap.get('numero_pagina');
    if (numeroPagina) {
      return this.clasificadorService.getEditorialPage(url, numeroPagina).take(1).map(editorial => {
        if (editorial.editorial != null) {
          return editorial;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      });
    } else {
      return this.clasificadorService.getEditorial(url).take(1).map(editorial => {
        if (editorial.editorial != null) {
          return editorial;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      });
    }
  }


}