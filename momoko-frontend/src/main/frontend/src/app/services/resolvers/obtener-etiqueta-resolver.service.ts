import { Injectable } from '@angular/core';
import { ClasificadorService } from '../clasificador.service';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { ObtenerPaginaGeneroResponse } from '../../dtos/response/obtenerPaginaGeneroResponse';
import { ObtenerPaginaCategoriaResponse } from '../../dtos/response/obtenerPaginaCategoriaResponse';
import { environment } from 'environments/environment';
import { ObtenerPaginaEtiquetaResponse } from '../../dtos/response/obtenerPaginaEtiquetaResponse';

@Injectable()
export class ObtenerListaEtiquetaResolverService implements Resolve<ObtenerPaginaEtiquetaResponse> {

  private log = environment.log;

  constructor(private clasificadorService: ClasificadorService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerPaginaEtiquetaResponse> {
    if (this.log) {
      console.log('Obteniendo lista etiqueta');
    }
    const url = route.paramMap.get('url_etiqueta');
    const numeroPagina = route.paramMap.get('numero_pagina');
    if (numeroPagina) {
      return this.clasificadorService.getEtiquetaPage(url, numeroPagina).take(1).map(etiqueta => {
        if (etiqueta.etiqueta != null) {
          return etiqueta;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      });
    } else {
      return this.clasificadorService.getEtiqueta(url).take(1).map(etiqueta => {
        if (etiqueta.etiqueta != null) {
          return etiqueta;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      });
    }
  }


}
