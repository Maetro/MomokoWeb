import { Injectable } from '@angular/core';

import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ClasificadorService } from '../clasificador.service';
import { ObtenerPaginaRedactorResponse } from '../../dtos/response/obtenerPaginaEditorResponse';
import {map, take} from 'rxjs/operators';


@Injectable()
export class ObtenerListaEditorResolverService implements Resolve<ObtenerPaginaRedactorResponse> {

  private log = environment.log;

  constructor(private clasificadorService: ClasificadorService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerPaginaRedactorResponse> {
    if (this.log) {
      console.log('Obteniendo lista Editor');
    }
    const url = route.paramMap.get('url_redactor');
    const numeroPagina = route.paramMap.get('numero_pagina');
    if (numeroPagina) {
      return this.clasificadorService.getEditorPage(url, numeroPagina).pipe(take(1),map(redactor => {
        if (redactor.redactor != null) {
          return redactor;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      }),);
    } else {
      return this.clasificadorService.getEditor(url).pipe(take(1),map(redactor => {
        if (redactor.redactor != null) {
          return redactor;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      }),);
    }
  }


}