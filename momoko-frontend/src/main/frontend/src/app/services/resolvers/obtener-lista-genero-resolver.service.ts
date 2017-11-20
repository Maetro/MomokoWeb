import { Injectable } from '@angular/core';
import { ClasificadorService } from 'app/services/clasificador.service';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { ObtenerPaginaGeneroResponse } from 'app/dtos/response/obtenerPaginaGeneroResponse';

@Injectable()
export class ObtenerListaGeneroResolverService implements Resolve<ObtenerPaginaGeneroResponse> {

    constructor(private clasificadorService: ClasificadorService, private router: Router) { }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerPaginaGeneroResponse> {
      console.log('Obteniendo lista Genero');
      const url = route.paramMap.get('url_genero');

      return this.clasificadorService.getGenero(url).take(1).map(genero => {
        if (genero) {
          return genero;
        } else { // url not found
          this.router.navigate(['/']);
          return null;
        }
      });
    }


}
