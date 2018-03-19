import { Injectable } from '@angular/core';
import { Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs/Observable';
import { FichaSaga } from '../../dtos/fichaSaga';
import { SagaService } from '../saga.service';


@Injectable()
export class ObtenerSagaResolverService implements Resolve<FichaSaga> {

  private log = environment.log;

  constructor(private sagaService: SagaService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FichaSaga> {
    if (this.log) {
      console.log('Obteniendo saga');
    }
    const urlSaga = route.paramMap.get('url_saga');

    return this.sagaService.getSaga(urlSaga).take(1).map(fichaSaga => {
      if (fichaSaga.saga != null) {
        return fichaSaga;
      } else { // url not found
        this.router.navigate(['/not-found']);
        return null;
      }
    });
  }
}