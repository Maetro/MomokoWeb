import { Injectable } from '@angular/core';
import {
  Resolve,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { FichaLibro } from '../../dtos/fichaLibro';
import { environment } from '../../../environments/environment';
import { LibroService } from '../libro.service';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';

@Injectable()
export class ObtenerLibroResolverService implements Resolve<FichaLibro> {
  private log = environment.log;

  constructor(private libroService: LibroService, private router: Router) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<FichaLibro> {
    if (this.log) {
      console.log('Obteniendo libro');
    }
    const url = route.paramMap.get('url');

    return this.libroService.getLibro(url).pipe(
      take(1),
      map(fichaLibro => {
        if (fichaLibro.libro != null) {
          return fichaLibro;
        } else {
          // url not found
          this.router.navigate(['/not-found']);
          return null;
        }
      })
    );
  }
}
