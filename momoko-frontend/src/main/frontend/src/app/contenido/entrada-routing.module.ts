import { ObtenerLibroResolverService } from '../services/resolvers/obtener-libro-resolver.service';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { ListaEntradasComponent } from '../gestion/gestion-entradas/lista-entradas/lista-entradas.component';
import { FichaLibroComponent } from './ficha-libro/ficha-libro.component';
import { AnalisisComponent } from './analisis/analisis.component';
import { ObtenerAnalisisResolverService } from '../services/resolvers/obtener-analisis-resolver.service';
import { ObtenerVideoResolverService } from '../services/resolvers/obtener-video-resolver.service';
import { ListaGeneroComponent } from './clasificador/lista-genero/lista-genero.component';
import { ObtenerListaGeneroResolverService } from '../services/resolvers/obtener-lista-genero-resolver.service';
import { MainContentComponent } from './main-content/main-content.component';
import { ObtenerListaCategoriaResolverService } from '../services/resolvers/obtener-lista-categoria-resolver.service';
import { ListaCategoriaComponent } from './clasificador/lista-categoria/lista-categoria.component';
import { ObtenerLibroNoticiasResolverService } from '../services/resolvers/obtener-libro-noticias-resolver.service';
import { ListaNoticiasLibroComponent } from './lista-noticias-libro/lista-noticias-libro.component';
import { VideosComponent } from './main-content/videos/videos.component';
import { PageNotFoundComponent } from '../error/page-not-found/page-not-found.component';
import { ObtenerListaEtiquetaResolverService } from '../services/resolvers/obtener-etiqueta-resolver.service';
import { ListaEtiquetaComponent } from './clasificador/lista-etiqueta/lista-etiqueta.component';

const fichaRoutes: Routes = [
  {
    path: 'libro/noticias/:url',
    component: ListaNoticiasLibroComponent,
    resolve: {
      noticiasLibro: ObtenerLibroNoticiasResolverService
    }
  },
  {
    path: 'libro/:url',
    component: FichaLibroComponent,
    resolve: {
      fichaLibro: ObtenerLibroResolverService
    }
  },
  {path: 'not-found', component: PageNotFoundComponent },
  {
    path: ':url',
    component: MainContentComponent,
    resolve: {
      obtenerEntradaResponse: ObtenerAnalisisResolverService
    }
  },
  {
    path: 'videos/:url',
    component: MainContentComponent,
    resolve: {
      obtenerEntradaResponse: ObtenerVideoResolverService
    }
  },
  {
    path: 'genero/:url_genero',
    component: ListaGeneroComponent,
    resolve: {
      paginaGeneroResponse: ObtenerListaGeneroResolverService
    }
  },
  {
    path: 'categoria/:url_categoria/:numero_pagina',
    component: ListaCategoriaComponent,
    resolve: {
      paginaCategoriaResponse: ObtenerListaCategoriaResolverService
    }
  },
  {
    path: 'categoria/:url_categoria',
    component: ListaCategoriaComponent,
    resolve: {
      paginaCategoriaResponse: ObtenerListaCategoriaResolverService
    }
  },
  {
    path: 'tag/:url_etiqueta',
    component: ListaEtiquetaComponent,
    resolve: {
      paginaEtiquetaResponse: ObtenerListaEtiquetaResolverService
    }
  },
  {
    path: 'tag/:url_etiqueta/:numero_pagina',
    component: ListaEtiquetaComponent,
    resolve: {
      paginaEtiquetaResponse: ObtenerListaEtiquetaResolverService
    }
  },
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
