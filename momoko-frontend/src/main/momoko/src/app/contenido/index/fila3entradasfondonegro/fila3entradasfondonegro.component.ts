import { Component, OnInit, AfterViewInit, Inject, PLATFORM_ID } from '@angular/core';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { environment } from '../../../../environments/environment';
import { isPlatformBrowser } from '@angular/common';
import { UtilService } from '../../../services/util/util.service';

declare var $: any;

@Component({
  selector: 'app-fila3entradasfondonegro',
  templateUrl: './fila3entradasfondonegro.component.html',
  styleUrls: ['./fila3entradasfondonegro.component.css']
})
export class Fila3entradasfondonegroComponent implements OnInit, AfterViewInit {

  private log = environment.log;

  entradas: EntradaSimple[];

  constructor( 
    @Inject(PLATFORM_ID) private platformId: Object,
    private util: UtilService
  ) { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      // Client only code.
      let maxHeight = 0;
      $('.zonablanca').each(function (index) {

        if (maxHeight < $(this).height()) {
          maxHeight = $(this).height();
        }
      });
      if (this.log) {
        console.log(maxHeight);
      }
      $('.zonablanca').css('height', maxHeight + 15);
    }

  }

  loadEntradas(entradasSimples: EntradaSimple[]) {
    this.entradas = entradasSimples;
  }

  obtenerUrlEntradaSimple(entrada: EntradaSimple): string{
    return this.util.obtenerUrlEntradaSimple(entrada);
  }

}
