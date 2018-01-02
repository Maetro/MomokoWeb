import { Injectable } from '@angular/core';
import { Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ClasificadorService } from 'app/services/clasificador.service';
import { ObtenerPaginaCategoriaResponse } from 'app/dtos/response/obtenerPaginaCategoriaResponse';
import { ObtenerPaginaLibroNoticiasResponse } from 'app/dtos/response/obtenerPaginaLibroNoticiasResponse';
import { Observable } from 'rxjs/Observable';
import { environment } from 'environments/environment';

@Injectable()
export class ObtenerLibroNoticiasResolverService implements Resolve<ObtenerPaginaLibroNoticiasResponse> {

  private log = environment.log;

  constructor(private clasificadorService: ClasificadorService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerPaginaLibroNoticiasResponse> {
    if (this.log) {
      console.log('Obteniendo lista noticias libro');
    }
    const url = route.paramMap.get('url');
    const numeroPagina = route.paramMap.get('numero_pagina');
    if (numeroPagina) {
      return this.clasificadorService.getPaginaNoticiasLibroPage(url, numeroPagina).take(1).map(libroNoticias => {
        if (libroNoticias.libro != null) {
          return libroNoticias;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      });
    } else {
      return this.clasificadorService.getPaginaNoticiasLibro(url).take(1).map(libroNoticias => {
        if (libroNoticias.libro != null) {
          return libroNoticias;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      });
    }
  }
}
