import { Component, OnInit, ViewChild } from '@angular/core';
import { IndexDataService } from 'app/services/index-data.service';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { DatePipe } from '@angular/common';
import { EntradaItem } from 'app/contenido/index/entrada-portada/entrada-item';
import { EntradaPortadaNormalImplComponent } from 'app/contenido/index/entrada-portada/entrada-portada-normal.component';
import { AnadirEntradaComponent } from 'app/contenido/index/entrada-portada/anadir-entrada.component';
import { AnadirEntrada2Component} from 'app/contenido/index/entrada-portada/anadir-entrada2.component';
import { LibroSimple } from 'app/dtos/libroSimple';


@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  ultimasEntradas: EntradaSimple[] = [];

  entradasPortada: EntradaItem[];
  librosMasLeidosMes: LibroSimple[];

  @ViewChild(AnadirEntradaComponent) anadirEntradaComponent: AnadirEntradaComponent;
  @ViewChild(AnadirEntrada2Component) anadirEntrada2Component: AnadirEntrada2Component;

  constructor(private indexDataService: IndexDataService) { }

  ngOnInit() {
    this.indexDataService.getIndexData().subscribe(datos => {
      console.log('Init index');
      this.ultimasEntradas = datos.ultimasEntradas;
      this.obtenerEntradasPortada(datos.ultimasEntradas);
      this.librosMasLeidosMes = datos.librosMasVistos;
    },
      error => {
        console.log('Error al recuperar los datos generales ', error);
      });

  }

  obtenerEntradasPortada(entradas: EntradaSimple[]) {
    this.entradasPortada = [];
    entradas.forEach(entradaSimple => {
      let e: EntradaItem;
      // if (entradaSimple.categoria === 'MISCELANEOS') {
        e = new EntradaItem(EntradaPortadaNormalImplComponent, entradaSimple);
      // }
      this.entradasPortada.push(e);
    });
    this.anadirEntradaComponent.loadComponent(this.entradasPortada[2]);
    this.anadirEntrada2Component.loadComponent(this.entradasPortada[1]);
  }

}
