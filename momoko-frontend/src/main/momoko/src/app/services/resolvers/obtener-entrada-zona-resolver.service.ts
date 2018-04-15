
import { Injectable } from '@angular/core';
import { Resolve, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';

import { Observable } from 'rxjs/Observable';
import { ObtenerEntradaResponse } from '../../dtos/response/obtenerEntradaResponse';
import { environment } from '../../../environments/environment';
import { EntradaService } from '../entrada.service';


@Injectable()
export class ObtenerEntradaZonaResolverService implements Resolve<ObtenerEntradaResponse> {

  private log = environment.log;

  constructor(private entradaService: EntradaService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerEntradaResponse> {
    if (this.log) {
      console.log('Obteniendo entrada zona');
    }
    const url_entrada = route.paramMap.get('url_entrada');


    return this.entradaService.getEntrada(url_entrada).take(1).map(entrada => {
      if (entrada.entrada != null) {
        return entrada;
      } else { // url not found
        this.router.navigate(['/not-found']);
        return null;
      }
    });
  }

}