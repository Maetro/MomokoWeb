import { LibroService } from './services/libro.service';
import { VideoService } from './services/video.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { Angulartics2GoogleAnalytics } from 'angulartics2/ga';
import { IndexComponent } from './contenido/index/index.component';
import { ObtenerIndexDataResolverService } from './services/resolvers/obtener-index-data-resolver.service';
import { ContenidoModule } from './contenido/contenido.module';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { Angulartics2Module } from 'angulartics2';
import { IndexDataService } from './services/index-data.service';
import { AppLoadModule } from './app-load/app-load.module';
import { ObtenerListaCategoriaResolverService } from './services/resolvers/obtener-lista-categoria-resolver.service';
import { ClasificadorService } from './services/clasificador.service';
import { ObtenerListaGeneroResolverService } from './services/resolvers/obtener-lista-genero-resolver.service';
import { ObtenerListaEtiquetaResolverService } from './services/resolvers/obtener-etiqueta-resolver.service';
import { ComentariosService } from './services/comentarios.service';
import { ObtenerEntradaResolverService } from './services/resolvers/obtener-entrada-resolver.service';
import { ObtenerVideoResolverService } from './services/resolvers/obtener-video-resolver.service';
import { EntradaService } from './services/entrada.service';
import { ScrollToService } from 'ng2-scroll-to-el';
import { ObtenerLibroNoticiasResolverService } from './services/resolvers/obtener-libro-noticias-resolver.service';
import { ObtenerLibroResolverService } from './services/resolvers/obtener-libro-resolver.service';
import { JsonAdapterService } from './services/util/json-adapter.service';
import { ObtenerListaBusquedaResolverService } from './services/resolvers/obtener-lista-busqueda-resolver.service';
import { PageNotFoundComponent } from './contenido/page-not-found/page-not-found.component';
import { PageSolicitudComponent } from './contenido/page-solicitud/page-solicitud.component';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    ContenidoModule,
    RouterModule,
    HttpClientModule,
    HttpModule,
    AppLoadModule,
    BrowserModule.withServerTransition({ appId: 'momoko-app' }),
    RouterModule.forRoot([
      {
        path: 'categoria/:url_categoria',
        loadChildren: './contenido/clasificador/lista-categoria/lista-categoria.module#ListaCategoriaModule',
        resolve: {
          paginaCategoriaResponse: ObtenerListaCategoriaResolverService
        }
      },
      {
        path: 'libro/noticias/:url',
        loadChildren: './contenido/libro/lista-noticias-libro/lista-noticias-libro.module#ListaNoticiasLibroModule',
        resolve: {
          noticiasLibro: ObtenerLibroNoticiasResolverService
        }
      },
      {
        path: 'libro/:url',
        loadChildren: './contenido/libro/ficha-libro/ficha-libro.module#FichaLibroModule',
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
        path: 'genero/:url_genero',
        loadChildren: './contenido/clasificador/lista-genero/lista-genero.module#ListaGeneroModule',
        resolve: {
          paginaGeneroResponse: ObtenerListaGeneroResolverService
        }
      },
      {
        path: 'tag/:url_etiqueta',
        loadChildren: './contenido/clasificador/lista-etiqueta/lista-etiqueta.module#ListaEtiquetaModule',
        resolve: {
          paginaEtiquetaResponse: ObtenerListaEtiquetaResolverService
        }
      },
      {
        path: 'buscar/:parametros_a_buscar',
        loadChildren: './contenido/clasificador/lista-busqueda/lista-busqueda.module#ListaBusquedaModule',
        resolve: {
          busqueda: ObtenerListaBusquedaResolverService
        }
      },
      {path: 'not-found', component: PageNotFoundComponent },
      {path: 'solicitud', component: PageSolicitudComponent },
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
    ]),
    Angulartics2Module.forRoot([Angulartics2GoogleAnalytics])
  ],
  providers: [ObtenerIndexDataResolverService, IndexDataService, ObtenerListaCategoriaResolverService, ClasificadorService,
    ObtenerListaGeneroResolverService, ObtenerListaEtiquetaResolverService, ComentariosService, ObtenerVideoResolverService, VideoService,
    ObtenerEntradaResolverService, EntradaService, ScrollToService, ObtenerLibroNoticiasResolverService, ObtenerLibroResolverService, LibroService,
    JsonAdapterService, ObtenerListaBusquedaResolverService],
  bootstrap: [AppComponent]
})
export class AppModule { }
