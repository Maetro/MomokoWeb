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
import { OrderType } from '../../../../dtos/enums/ordertype';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-genero-vista-grid',
  templateUrl: './genero-vista-grid.component.html',
  styleUrls: ['./genero-vista-grid.component.css']
})
export class GeneroVistaGridComponent implements OnInit, OnDestroy {
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
