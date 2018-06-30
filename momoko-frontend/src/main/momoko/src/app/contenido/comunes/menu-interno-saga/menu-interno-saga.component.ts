import { DatoEntrada } from './../../../dtos/datoEntrada';
import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Libro } from '../../../dtos/libro';
import { ActivatedRoute, Router } from '@angular/router';
import { Saga } from '../../../dtos/saga';

@Component({
  selector: 'app-menu-interno-saga',
  templateUrl: './menu-interno-saga.component.html',
  styleUrls: ['./menu-interno-saga.component.css']
})
export class MenuInternoSagaComponent implements OnInit {

  private log = environment.log;

  urlVideo: string;
  urlAnalisis: string;
  hayVideo = false;
  hayAnalisis = false;
  hayNoticias = false;
  hayGuia = false;
  menuLibroExtra: DatoEntrada[];

  @Input() saga: Saga;

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    if (this.log) {
      console.log('Iniciando menu');
    }

  }

  isActive(instruction: any[]): boolean {
    // Set the second parameter to true if you want to require an exact match.
    return this.router.isActive(this.router.createUrlTree(instruction), false);
  }



}
