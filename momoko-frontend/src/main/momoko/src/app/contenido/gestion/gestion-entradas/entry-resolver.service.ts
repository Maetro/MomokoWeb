import {map, take} from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { Resolve, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';

import { Observable } from 'rxjs';

import { Entrada } from '../../../dtos/entrada';
import { EntryService } from './entry.service';
import { environment } from 'environments/environment';


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