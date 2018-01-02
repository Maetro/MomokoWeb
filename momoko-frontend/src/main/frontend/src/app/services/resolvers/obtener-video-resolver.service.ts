import { EntradaService } from 'app/services/entrada.service';
import { Injectable } from '@angular/core';
import { Resolve, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Entrada } from 'app/dtos/entrada';
import { Observable } from 'rxjs/Observable';
import { VideoService } from 'app/services/video.service';
import { ObtenerEntradaResponse } from 'app/dtos/response/obtenerEntradaResponse';
import { environment } from 'environments/environment';

@Injectable()
export class ObtenerVideoResolverService implements Resolve<ObtenerEntradaResponse> {

  private log = environment.log;

  constructor(private videoService: VideoService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObtenerEntradaResponse> {

      console.log('Obteniendo video');

    const url = route.paramMap.get('url');

    return this.videoService.getEntrada(url).take(1).map(entrada => {
      if (entrada.entrada != null) {
        this.router.navigate(['/' + url]);
      } else { // url not found
        this.router.navigate(['/not-found']);
        return null;
      }
    });
  }

}
