import { ObtenerLibroResolverService } from './../services/resolvers/obtener-libro-resolver.service';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { ListaEntradasComponent } from 'app/gestion/gestion-entradas/lista-entradas/lista-entradas.component';
import { FichaLibroComponent } from 'app/contenido/ficha-libro/ficha-libro.component';
import { AnalisisComponent } from 'app/contenido/analisis/analisis.component';
import { ObtenerAnalisisResolverService } from 'app/services/resolvers/obtener-analisis-resolver.service';
import { ObtenerVideoResolverService } from 'app/services/resolvers/obtener-video-resolver.service';
import { ListaGeneroComponent } from 'app/contenido/clasificador/lista-genero/lista-genero.component';
import { ObtenerListaGeneroResolverService } from 'app/services/resolvers/obtener-lista-genero-resolver.service';

const fichaRoutes: Routes = [
  {
    path: 'libro/:url',
    component: FichaLibroComponent,
    resolve: {
      fichaLibro: ObtenerLibroResolverService
    }
  },
  {
    path: ':url',
    component: AnalisisComponent,
    resolve: {
      fichaEntrada: ObtenerAnalisisResolverService
    }
  },
  {
    path: 'videos/:url',
    component: AnalisisComponent,
    resolve: {
      fichaEntrada: ObtenerVideoResolverService
    }
  },
  {
    path: ':url_categoria/:url_genero',
    component: ListaGeneroComponent,
    resolve: {
      paginaGeneroResponse: ObtenerListaGeneroResolverService
    }
  }
  /*{
    path: 'patient',
    component: EntradaComponent,
    children: [
      {
        path: '',
        children: [
          {
            path: 'home',
            component: EntradaComponent,
          }
        ],
      }
    ]
  }*/
];

@NgModule({
  imports: [
    RouterModule.forChild(fichaRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class EntradaRoutingModule { }
