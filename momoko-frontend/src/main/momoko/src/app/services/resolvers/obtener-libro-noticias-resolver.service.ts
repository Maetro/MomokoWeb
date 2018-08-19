import { Injectable } from '@angular/core';
import { ObtenerPaginaColeccionLibroResponse } from '../../dtos/response/obtenerPaginaLibroNoticiasResponse';
import {
  Resolve,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { environment } from '../../../environments/environment';
import { ClasificadorService } from '../clasificador.service';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';

@Injectable()
export class ObtenerLibroNoticiasResolverService
  implements Resolve<ObtenerPaginaColeccionLibroResponse> {
  private log = environment.log;

  constructor(
    private clasificadorService: ClasificadorService,
    private router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<ObtenerPaginaColeccionLibroResponse> {
    if (this.log) {
      console.log('Obteniendo lista noticias libro');
    }
    const url = route.paramMap.get('url-libro');
    const numeroPagina = route.paramMap.get('numero_pagina');
    if (numeroPagina) {
      return this.clasificadorService
        .getPaginaNoticiasLibroPage(url, numeroPagina)
        .pipe(
          take(1),
          map(libroNoticias => {
            if (libroNoticias.libro != null) {
              return libroNoticias;
            } else {
              // url not found
              this.router.navigate(['/not-found']);
              return null;
            }
          })
        );
    } else {
      return this.clasificadorService
        .getPaginaNoticiasLibro(url)
        .pipe(
        take(1),
        map(libroNoticias => {
          if (libroNoticias.libro != null) {
            return libroNoticias;
          } else {
            // url not found
            this.router.navigate(['/not-found']);
            return null;
          }
        }),);
    }
  }
}
