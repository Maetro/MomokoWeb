import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { GenrePageResponse } from '../../dtos/genre/genrePageResponse';
import { Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ClasificadorService } from '../clasificador.service';
import { Observable } from 'rxjs/Observable';
import { OrderType } from '../../dtos/enums/ordertype';

@Injectable()
export class ObtenerListaGeneroResolverService implements Resolve<GenrePageResponse> {

  private log = environment.log;

  constructor(private clasificadorService: ClasificadorService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<GenrePageResponse> {
    if (this.log) {
      console.log('Obteniendo lista Genero');
    }
    const url = route.paramMap.get('url_genero');

    let numeroPagina = route.paramMap.get('numero_pagina');
    if (!numeroPagina) {
      numeroPagina = "1";
    }

      return this.clasificadorService.getGenrePage(url, numeroPagina, OrderType.DATE).take(1).map(genero => {
        if (genero.genero != null) {
          return genero;
        } else { // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      });
    
  }


}
