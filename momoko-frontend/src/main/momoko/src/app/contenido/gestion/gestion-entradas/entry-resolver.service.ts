import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from '@angular/router';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Entrada } from '../../../dtos/entrada';
import { EntryService } from './entry.service';





@Injectable({
  providedIn: 'root'
})
export class EntryResolverService implements Resolve<Entrada> {

  private log = environment.log;

  constructor(private entryService: EntryService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Entrada> {
    if (this.log) {
      console.log('Obteniendo opiniones 1');
    }
    const url = route.paramMap.get('url');

    return this.entryService.getEntradaAdmin(url).pipe(map(entrada => {
      if (entrada != null) {
        return entrada;
      } else { // url not found
        this.router.navigate(['/not-found']);
        return null;
      }
    }));
  }

}