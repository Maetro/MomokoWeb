import { Injectable } from '@angular/core';
import { Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ObtenerPaginaEtiquetaResponse } from '../../dtos/response/obtenerPaginaEtiquetaResponse';
import { environment } from '../../../environments/environment';
import { ClasificadorService } from '../clasificador.service';
import { Observable } from 'rxjs/Observable';

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

