import { Component, OnDestroy, OnInit, Input } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from '../../../../../environments/environment';
import { LibroSimple } from '../../../../dtos/libroSimple';
import { Genero } from '../../../../dtos/genero';


@Component({
  selector: 'app-genero-vista-lista',
  templateUrl: './genero-vista-lista.component.html',
  styleUrls: ['./genero-vista-lista.component.css']
})
export class GeneroVistaListaComponent implements OnInit, OnDestroy {

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
