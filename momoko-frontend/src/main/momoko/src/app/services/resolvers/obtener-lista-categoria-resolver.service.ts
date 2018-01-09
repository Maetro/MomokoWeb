import { Injectable } from '@angular/core';

import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment';
import { ClasificadorService } from '../clasificador.service';
import { ObtenerPaginaCategoriaResponse } from '../../dtos/response/obtenerPaginaCategoriaResponse';


@Injectable()
export class ObtenerListaCategoriaResolverService implements Resolve<ObtenerPaginaCategoriaResponse> {

  private log = environment.log;

  constructor(private clasificadorService: ClasificadorService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerPaginaCategoriaResponse> {
    if (this.log) {
      console.log('Obteniendo lista Categoria');
    }
    const url = route.paramMap.get('url_categoria');
    const numeroPagina = route.paramMap.get('numero_pagina');
    if (numeroPagina) {
      return this.clasificadorService.getCategoriaPage(url, numeroPagina).take(1).map(categoria => {
        if (categoria.categoria != null) {
          return categoria;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      });
    } else {
      return this.clasificadorService.getCategoria(url).take(1).map(categoria => {
        if (categoria.categoria != null) {
          return categoria;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      });
    }
  }


}
