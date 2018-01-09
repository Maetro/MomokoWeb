import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { ObtenerPaginaGeneroResponse } from '../../dtos/response/obtenerPaginaGeneroResponse';
import { Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ClasificadorService } from '../clasificador.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ObtenerListaGeneroResolverService implements Resolve<ObtenerPaginaGeneroResponse> {

  private log = environment.log;

  constructor(private clasificadorService: ClasificadorService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerPaginaGeneroResponse> {
    if (this.log) {
      console.log('Obteniendo lista Genero');
    }
    const url = route.paramMap.get('url_genero');

    return this.clasificadorService.getGenero(url).take(1).map(genero => {
      if (genero.genero != null) {
        return genero;
      } else { // url not found
        this.router.navigate(['/not-found']);
        return null;
      }
    });
  }


}
