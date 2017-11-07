import { Injectable } from '@angular/core';
import { Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { LibroService } from 'app/services/libro.service';
import { Observable } from 'rxjs/Observable';
import { FichaLibro } from 'app/dtos/fichaLibro';

@Injectable()
export class ObtenerLibroResolverService implements Resolve<FichaLibro> {

  constructor(private libroService: LibroService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FichaLibro> {
    console.log('Obteniendo libro');
    const url = route.paramMap.get('url');

    return this.libroService.getLibro(url).take(1).map(fichaLibro => {
      if (fichaLibro) {
        return fichaLibro;
      } else { // url not found
        this.router.navigate(['/']);
        return null;
      }
    });
  }
}
