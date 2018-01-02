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
import { MainContentComponent } from 'app/contenido/main-content/main-content.component';
import { ObtenerListaCategoriaResolverService } from 'app/services/resolvers/obtener-lista-categoria-resolver.service';
import { ListaCategoriaComponent } from 'app/contenido/clasificador/lista-categoria/lista-categoria.component';
import { ObtenerLibroNoticiasResolverService } from 'app/services/resolvers/obtener-libro-noticias-resolver.service';
import { ListaNoticiasLibroComponent } from 'app/contenido/lista-noticias-libro/lista-noticias-libro.component';
import { VideosComponent } from 'app/contenido/main-content/videos/videos.component';
import { PageNotFoundComponent } from 'app/error/page-not-found/page-not-found.component';

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
