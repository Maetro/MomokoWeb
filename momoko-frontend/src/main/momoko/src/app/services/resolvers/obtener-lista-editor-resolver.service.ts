import { Injectable } from '@angular/core';

import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment';
import { ClasificadorService } from '../clasificador.service';
import { ObtenerPaginaEditorResponse } from '../../dtos/response/obtenerPaginaEditorResponse';



@Injectable()
export class ObtenerListaEditorResolverService implements Resolve<ObtenerPaginaEditorResponse> {

  private log = environment.log;

  constructor(private clasificadorService: ClasificadorService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerPaginaEditorResponse> {
    if (this.log) {
      console.log('Obteniendo lista Editor');
    }
    const url = route.paramMap.get('url_editor');
    const numeroPagina = route.paramMap.get('numero_pagina');
    if (numeroPagina) {
      return this.clasificadorService.getEditorPage(url, numeroPagina).take(1).map(redactor => {
        if (redactor.autor != null) {
          return redactor;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      });
    } else {
      return this.clasificadorService.getEditor(url).take(1).map(redactor => {
        if (redactor.autor != null) {
          return redactor;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      });
    }
  }


}