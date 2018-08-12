
import { EntradaService } from '../entrada.service';
import { Injectable } from '@angular/core';
import { Resolve, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Entrada } from '../../dtos/entrada';
import { Observable } from 'rxjs/Observable';
import { ObtenerEntradaResponse } from '../../dtos/response/obtenerEntradaResponse';
import { environment } from 'environments/environment';

@Injectable()
export class ObtenerAnalisisResolverService implements Resolve<ObtenerEntradaResponse> {

  private log = environment.log;

  constructor(private entradaService: EntradaService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerEntradaResponse> {
    if (this.log) {
      console.log('Obteniendo analisis');
    }
    const url = route.paramMap.get('url');

    return this.entradaService.getEntrada(url).take(1).map(entrada => {
      if (entrada.entrada != null) {
        return entrada;
      } else { // url not found
        this.router.navigate(['/not-found']);
        return null;
      }
    });
  }

}
