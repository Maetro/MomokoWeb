import { TestService } from './services/test.service';
import { PageCriteriosComponent } from './contenido/estaticas/page-criterios/page-criterios.component';
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
import { PageNotFoundComponent } from './contenido/estaticas/page-not-found/page-not-found.component';
import { PageSolicitudComponent } from './contenido/estaticas/page-solicitud/page-solicitud.component';
import { EstaticasModule } from './contenido/estaticas/estaticas.module';
import { makeDecorator } from '@angular/core/src/util/decorators';
import { ObtenerEntradaZonaResolverService } from './services/resolvers/obtener-entrada-zona-resolver.service';
import { ObtenerResenaResolverService } from './services/resolvers/obtener-resena-resolver.service';
import { LinkService } from './services/link.service';
import { UtilService } from './services/util/util.service';
import { ObtenerSagaResolverService } from './services/resolvers/obtener-saga-resolver.service';
import { SagaService } from './services/saga.service';
import { ObtenerLiveTestResolverService } from './services/resolvers/obtener-live-test-resolver.service';
import { AppRoutingModule } from './app-routing.module';


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
    EstaticasModule,
    BrowserModule.withServerTransition({ appId: 'momoko-app' }),
    AppRoutingModule,
    Angulartics2Module.forRoot([Angulartics2GoogleAnalytics])
  ],
  providers: [ObtenerIndexDataResolverService, IndexDataService, ObtenerListaCategoriaResolverService, ClasificadorService,
    ObtenerListaGeneroResolverService, ObtenerListaEtiquetaResolverService, ComentariosService, ObtenerVideoResolverService, VideoService,
    ObtenerEntradaResolverService, EntradaService, ScrollToService, ObtenerLibroNoticiasResolverService, ObtenerLibroResolverService, LibroService,
    JsonAdapterService, ObtenerListaBusquedaResolverService, ObtenerEntradaZonaResolverService, LinkService, ObtenerSagaResolverService, SagaService,
    ObtenerResenaResolverService, UtilService, ObtenerLiveTestResolverService, TestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
