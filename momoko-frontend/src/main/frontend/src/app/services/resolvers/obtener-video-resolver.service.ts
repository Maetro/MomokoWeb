import { EntradaService } from 'app/services/entrada.service';
import { Injectable } from '@angular/core';
import { Resolve, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Entrada } from 'app/dtos/entrada';
import { Observable } from 'rxjs/Observable';
import { FichaEntrada } from 'app/dtos/fichaEntrada';
import { VideoService } from 'app/services/video.service';

@Injectable()
export class ObtenerVideoResolverService implements Resolve<FichaEntrada> {

  constructor(private videoService: VideoService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FichaEntrada> {
    console.log('Obteniendo video');
    const url = route.paramMap.get('url');

    return this.videoService.getEntrada(url).take(1).map(entrada => {
      if (entrada) {
        return entrada;
      } else { // url not found
        this.router.navigate(['/']);
        return null;
      }
    });
  }

}
