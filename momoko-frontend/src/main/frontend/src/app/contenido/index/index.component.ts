import { Fila3entradasfondonegroComponent } from './fila3entradasfondonegro/fila3entradasfondonegro.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { IndexDataService } from 'app/services/index-data.service';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { DatePipe } from '@angular/common';
import { EntradaItem } from 'app/contenido/index/entrada-portada/entrada-item';
import { EntradaPortadaNormalImplComponent } from 'app/contenido/index/entrada-portada/entrada-portada-normal.component';
import { AnadirEntradaComponent } from 'app/contenido/index/entrada-portada/anadir-entrada.component';
import { AnadirEntrada2Component} from 'app/contenido/index/entrada-portada/anadir-entrada2.component';
import { LibroSimple } from 'app/dtos/libroSimple';
import { LibrosHorizontalComponent } from 'app/contenido/index/libros-horizontal/libros-horizontal.component';


@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  ultimasEntradas: EntradaSimple[] = [];
  ultimas3Entradas: EntradaSimple[] = [];
  entradasPortada: EntradaItem[];
  librosMasLeidosMes: LibroSimple[];
  librosUltimosAnalisis: LibroSimple[];

  portada = "http://elmiedodelosesclavos.com/wp-content/uploads/2017/01/El-miedo-de-los-esclavos-2-345x520.jpg";

  tituloUltimosAnalisis = 'Últimos análisis';

  @ViewChild(AnadirEntradaComponent) anadirEntradaComponent: AnadirEntradaComponent;
  @ViewChild(AnadirEntrada2Component) anadirEntrada2Component: AnadirEntrada2Component;
  @ViewChild(Fila3entradasfondonegroComponent) fila3entradasfondonegroComponent: Fila3entradasfondonegroComponent;
  @ViewChild(LibrosHorizontalComponent) librosHorizontalComponent: LibrosHorizontalComponent;

  constructor(private indexDataService: IndexDataService) { }

  ngOnInit() {
    this.indexDataService.getIndexData().subscribe(datos => {
      console.log('Init index');
      this.ultimasEntradas = datos.ultimasEntradas;
      this.obtenerEntradasPortada(datos.ultimasEntradas);
      this.librosMasLeidosMes = datos.librosMasVistos;
      this.librosUltimosAnalisis = datos.librosMasVistos;
    },
      error => {
        console.log('Error al recuperar los datos generales ', error);
      });

  }

  obtenerEntradasPortada(entradas: EntradaSimple[]) {
    this.entradasPortada = [];
    let entradasBD: EntradaItem[];
    entradasBD = [];
    entradas.forEach(entradaSimple => {
      let e: EntradaItem;
      // if (entradaSimple.categoria === 'MISCELANEOS') {
        e = new EntradaItem(EntradaPortadaNormalImplComponent, entradaSimple);
      // }
      entradasBD.push(e);
    });
    this.entradasPortada = entradasBD;
    this.anadirEntradaComponent.loadComponent(this.entradasPortada[2]);
    this.anadirEntrada2Component.loadComponent(this.entradasPortada[1]);

    for (let pos = this.ultimasEntradas.length - 3; pos < this.ultimasEntradas.length; pos++) {
      this.ultimas3Entradas.push(this.ultimasEntradas[pos]);
    }
    this.fila3entradasfondonegroComponent.loadEntradas(this.ultimas3Entradas);
  }

}
