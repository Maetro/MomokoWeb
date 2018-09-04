import { OrderType } from '../../../../dtos/enums/ordertype';
import {
  Component,
  OnDestroy,
  OnInit,
  Input,
  Inject,
  PLATFORM_ID
} from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from '../../../../../environments/environment';
import { LibroSimple } from '../../../../dtos/libroSimple';
import { Genero } from '../../../../dtos/genre/genero';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-genero-vista-lista',
  templateUrl: './genero-vista-lista.component.html',
  styleUrls: ['./genero-vista-lista.component.css']
})
export class GeneroVistaListaComponent implements OnInit, OnDestroy {
  private log = environment.log;

  isMobile = false;

  @Input()
  genero: Genero;
  @Input()
  librosGenero: LibroSimple[];
  @Input()
  numbers: any;
  @Input()
  orderby: OrderType;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      if (window.screen.width < 768) {
        this.isMobile = true;
      }
    }
  }

  ngOnDestroy() {}
}
