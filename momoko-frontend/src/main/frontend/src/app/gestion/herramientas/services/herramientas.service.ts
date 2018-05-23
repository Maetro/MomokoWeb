import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { environment } from 'environments/environment.prod';
import { HttpClient } from 'selenium-webdriver/http';

@Injectable()
export class HerramientasService {

log = environment.log;

constructor(
  private http: HttpClient
) { }

getEntradasConUrlsLargas(): Observable<EntradaSimple[]> {
  const headers = new HttpHeaders({
    'Content-type': 'application/json',
    'Authorization': 'Bearer ' + Cookie.get('access_token')
  });
  return this.http.get( GLOBAL.api + 'attestation/images/' + this.attestationId.toString()
  );

}

/*.subscribe(
      (r: EntradaSimple) => {
          this.json = r;
          if (this.json.vuelo !== undefined) {
              this.json.vuelo.forEach(vuelo => {
                  this.imageSourcesVuelo.push(vuelo.image);
              });
          }
          if (this.json.cars !== undefined) {
              this.json.cars.forEach(item => {
                  this.imageSourcesCars.push(item);
                  this.imageVisualizationSourcesCars.push(item);
                  if (item.licensePlate != undefined && item.licensePlate != null) {
                      this.cars.push(item.licensePlate);
                  }
              });
              this.cars = Array.from(new Set(this.cars));
              this.cars.push('ALL');
          }
          if(this.json.actors!==undefined){
              this.json.actors.forEach(item=>{
                  this.imageActors.push(item);
                  this.imageVisualizationActors.push(item);
                  if(item.dni!==undefined && item.dni!==null){
                      this.actors.push(item.dni);
                  }
              });
              this.actors = Array.from(new Set(this.actors));
              this.actors.push('ALL');
          }
          this.imagesLoaded = true;
      },
      e => {
          this.json = null;
          console.log('Attestation not found');
      });*/ 

private obtenerSagasDeRespuesta(res: Saga[]) {
  return res;
}

}
