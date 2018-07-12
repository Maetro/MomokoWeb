import { Component, OnDestroy, OnInit, Input } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from '../../../../../environments/environment';
import { LibroSimple } from '../../../../dtos/libroSimple';
import { Genero } from '../../../../dtos/genero';


@Component({
  selector: 'app-genero-vista-grid',
  templateUrl: './genero-vista-grid.component.html',
  styleUrls: ['./genero-vista-grid.component.css']
})
export class GeneroVistaGridComponent implements OnInit, OnDestroy {

  private log = environment.log;

  isMobile = false;

  @Input() genero: Genero;
  @Input() librosGenero: LibroSimple[];
  @Input() numbers: any;
  

  constructor() { }

  ngOnInit() {
    if (window.screen.width < 768){
      this.isMobile = true;
    }
  }

  ngOnDestroy() {
  }

}
