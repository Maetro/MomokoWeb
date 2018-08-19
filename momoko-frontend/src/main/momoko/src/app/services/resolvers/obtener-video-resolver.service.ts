import { Injectable } from '@angular/core';
import {
  Resolve,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { ObtenerEntradaResponse } from '../../dtos/response/obtenerEntradaResponse';
import { environment } from '../../../environments/environment';
import { VideoService } from '../video.service';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';

@Injectable()
export class ObtenerVideoResolverService
  implements Resolve<ObtenerEntradaResponse> {
  private log = environment.log;

  constructor(private videoService: VideoService, private router: Router) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<ObtenerEntradaResponse> {
    console.log('Obteniendo video');

    const url = route.paramMap.get('url');

    return this.videoService.getEntrada(url).pipe(
      take(1),
      map(entrada => {
        if (entrada.entrada != null) {
          this.router.navigate(['/' + url]);
        } else {
          // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      })
    );
  }
}
