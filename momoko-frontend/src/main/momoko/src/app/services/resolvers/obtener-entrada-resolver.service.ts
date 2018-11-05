
import {map, take} from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { Resolve, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';

import { Observable } from 'rxjs';
import { ObtenerEntradaResponse } from '../../dtos/response/obtenerEntradaResponse';
import { environment } from '../../../environments/environment';
import { EntradaService } from '../entrada.service';


@Injectable()
export class ObtenerEntradaResolverService implements Resolve<ObtenerEntradaResponse> {

  private log = environment.log;

  constructor(private entradaService: EntradaService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerEntradaResponse> {
    if (this.log) {
      console.log('Obteniendo opiniones 1');
    }
    const url = route.paramMap.get('url');
    return this.entradaService.getEntrada(url);
  }

}