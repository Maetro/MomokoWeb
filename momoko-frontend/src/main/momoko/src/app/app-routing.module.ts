import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from './contenido/auth/services/auth-guard.service';
import { PageCriteriosComponent } from './contenido/estaticas/page-criterios/page-criterios.component';
import { PageNotFoundComponent } from './contenido/estaticas/page-not-found/page-not-found.component';
import { PageSolicitudComponent } from './contenido/estaticas/page-solicitud/page-solicitud.component';
import { ObtenerEntradaResolverService } from './services/resolvers/obtener-entrada-resolver.service';
import { ObtenerEntradaZonaResolverService } from './services/resolvers/obtener-entrada-zona-resolver.service';
import { ObtenerListaEtiquetaResolverService } from './services/resolvers/obtener-etiqueta-resolver.service';
import { ObtenerIndexDataResolverService } from './services/resolvers/obtener-index-data-resolver.service';
import { ObtenerLibroMiscelaneosResolverService } from './services/resolvers/obtener-libro-miscelaneos-resolver.service';
import { ObtenerLibroNoticiasResolverService } from './services/resolvers/obtener-libro-noticias-resolver.service';
import { ObtenerLibroResolverService } from './services/resolvers/obtener-libro-resolver.service';
import { ObtenerListaBusquedaResolverService } from './services/resolvers/obtener-lista-busqueda-resolver.service';
import { ObtenerListaCategoriaResolverService } from './services/resolvers/obtener-lista-categoria-resolver.service';
import { ObtenerListaEditorResolverService } from './services/resolvers/obtener-lista-editor-resolver.service';
import { ObtenerListaEditoralResolverService } from './services/resolvers/obtener-lista-editoral-resolver.service';
import { ObtenerListaGeneroResolverService } from './services/resolvers/obtener-lista-genero-resolver.service';
import { ObtenerResenaResolverService } from './services/resolvers/obtener-resena-resolver.service';
import { ObtenerSagaMiscelaneosResolverService } from './services/resolvers/obtener-saga-miscelaneos.resolver.service';
import { ObtenerSagaNoticiasResolverService } from './services/resolvers/obtener-saga-noticias.resolver.service';
import { ObtenerSagaResolverService } from './services/resolvers/obtener-saga-resolver.service';
import { ObtenerVideoResolverService } from './services/resolvers/obtener-video-resolver.service';

const appRoutes: Routes = [
  {
    path: 'categoria/:url_categoria',
    loadChildren:
      './contenido/clasificador/lista-categoria/lista-categoria.module#ListaCategoriaModule',
    resolve: {
      paginaCategoriaResponse: ObtenerListaCategoriaResolverService
    }
  },
  {
    path: 'noticias-libro/:url-libro',
    loadChildren:
      './contenido/libro/lista-noticias-libro/lista-noticias-libro.module#ListaNoticiasLibroModule',
    resolve: {
      noticiasLibro: ObtenerLibroNoticiasResolverService
    }
  },
  {
    path: 'gestion',
    loadChildren: 'app/contenido/gestion/gestion.module#GestionModule',
    canLoad: [AuthGuardService]
  },
  {
    path: 'noticias-saga/:url-saga',
    loadChildren:
      './contenido/saga/lista-noticias-saga/lista-noticias-saga.module#ListaNoticiasSagaModule',
    resolve: {
      coleccionSaga: ObtenerSagaNoticiasResolverService
    }
  },
  {
    path: 'miscelaneos-libro/:url-libro',
    loadChildren:
      './contenido/libro/lista-miscelaneos-libro/lista-miscelaneos-libro.module#ListaMiscelaneosLibroModule',
    resolve: {
      coleccionLibro: ObtenerLibroMiscelaneosResolverService
    }
  },
  {
    path: 'miscelaneos-saga/:url-saga',
    loadChildren:
      './contenido/saga/lista-miscelaneos-saga/lista-miscelaneos-saga.module#ListaMiscelaneosSagaModule',
    resolve: {
      coleccionSaga: ObtenerSagaMiscelaneosResolverService
    }
  },
  {
    path: 'saga/:url_saga',
    loadChildren:
      './contenido/saga/ficha-saga/ficha-saga.module#FichaSagaModule',
    resolve: {
      fichaSaga: ObtenerSagaResolverService
    }
  },
  {
    path: 'libro/:url',
    loadChildren:
      './contenido/libro/ficha-libro/ficha-libro.module#FichaLibroModule',
    resolve: {
      fichaLibro: ObtenerLibroResolverService
    }
  },
  {
    path: 'videos/:url',
    loadChildren: './contenido/entrada/entrada.module#EntradaModule',
    resolve: {
      obtenerEntradaResponse: ObtenerVideoResolverService
    }
  },
  {
    path: 'redactor/:url_redactor',
    loadChildren:
      './contenido/clasificador/lista-editor/lista-editor.module#ListaEditorModule',
    resolve: {
      redactor: ObtenerListaEditorResolverService
    }
  },
  {
    path: 'editorial/:url_editorial',
    loadChildren:
      './contenido/clasificador/lista-editorial/lista-editorial.module#ListaEditorialModule',
    resolve: {
      editorial: ObtenerListaEditoralResolverService
    }
  },
  {
    path: 'genero/:url_genero/:numero_pagina',
    loadChildren:
      './contenido/clasificador/lista-genero/lista-genero.module#ListaGeneroModule',
    resolve: {
      paginaGeneroResponse: ObtenerListaGeneroResolverService
    }
  },
  {
    path: 'genero/:url_genero',
    loadChildren:
      './contenido/clasificador/lista-genero/lista-genero.module#ListaGeneroModule',
    resolve: {
      paginaGeneroResponse: ObtenerListaGeneroResolverService
    }
  },
  {
    path: 'tag/:url_etiqueta',
    loadChildren:
      './contenido/clasificador/lista-etiqueta/lista-etiqueta.module#ListaEtiquetaModule',
    resolve: {
      paginaEtiquetaResponse: ObtenerListaEtiquetaResolverService
    }
  },
  { path: 'analisis/:url-entrada',
    redirectTo: 'opiniones/:url-entrada',
  },
  {
    path: 'opiniones/:url_entrada',
    loadChildren: './contenido/entrada/entrada.module#EntradaModule',
    resolve: {
      obtenerEntradaResponse: ObtenerResenaResolverService
    }
  },
  {
    path: 'noticia/:url_entrada',
    loadChildren: './contenido/entrada/entrada.module#EntradaModule',
    resolve: {
      obtenerEntradaResponse: ObtenerResenaResolverService
    }
  },
  {
    path: 'buscar/:parametros_a_buscar',
    loadChildren:
      './contenido/clasificador/lista-busqueda/lista-busqueda.module#ListaBusquedaModule',
    resolve: {
      busqueda: ObtenerListaBusquedaResolverService
    }
  },
  {
    path: ':url_zona/:url_entrada',
    loadChildren: './contenido/entrada/entrada.module#EntradaModule',
    resolve: {
      obtenerEntradaResponse: ObtenerEntradaZonaResolverService
    }
  },
  { path: 'not-found', component: PageNotFoundComponent },
  { path: 'solicitud', component: PageSolicitudComponent },
  { path: 'criterios', component: PageCriteriosComponent },
  {
    path: ':url',
    loadChildren: './contenido/entrada/entrada.module#EntradaModule',
    resolve: {
      obtenerEntradaResponse: ObtenerEntradaResolverService
    }
  },
  {
    path: '',
    loadChildren: './contenido/index/index.module#IndexModule',
    resolve: {
      obtenerIndexDataResponse: ObtenerIndexDataResolverService
    }
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: false } // <-- debugging purposes only
    )
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
