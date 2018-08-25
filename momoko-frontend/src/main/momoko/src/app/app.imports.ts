import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { AppLoadModule } from './app-load/app-load.module';
import { AppRoutingModule } from './app-routing.module';
import { Globals } from './app.globals';
import { AuthModule } from './contenido/auth/auth.module';
import { ContenidoModule } from './contenido/contenido.module';
import { EstaticasModule } from './contenido/estaticas/estaticas.module';
import { ClasificadorService } from './services/clasificador.service';
import { ComentariosService } from './services/comentarios.service';
import { EntradaService } from './services/entrada.service';
import { IndexDataService } from './services/index-data.service';
import { LibroService } from './services/libro.service';
import { LinkService } from './services/link.service';
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
import { ObtenerLiveTestResolverService } from './services/resolvers/obtener-live-test-resolver.service';
import { ObtenerResenaResolverService } from './services/resolvers/obtener-resena-resolver.service';
import { ObtenerSagaMiscelaneosResolverService } from './services/resolvers/obtener-saga-miscelaneos.resolver.service';
import { ObtenerSagaNoticiasResolverService } from './services/resolvers/obtener-saga-noticias.resolver.service';
import { ObtenerSagaResolverService } from './services/resolvers/obtener-saga-resolver.service';
import { ObtenerVideoResolverService } from './services/resolvers/obtener-video-resolver.service';
import { SagaService } from './services/saga.service';
import { TestService } from './services/test.service';
import { JsonAdapterService } from './services/util/json-adapter.service';
import { UtilService } from './services/util/util.service';
import { VideoService } from './services/video.service';
import { AuthGuardService } from './contenido/auth/services/auth-guard.service';
import { AuthRoutingModule } from './contenido/auth/auth-app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import {ToastModule} from 'primeng/toast';
import { MessageService } from 'primeng/primeng';

export const PROVIDERS = [
    ObtenerIndexDataResolverService,
    IndexDataService,
    ObtenerListaCategoriaResolverService,
    ClasificadorService,
    ObtenerListaGeneroResolverService,
    ObtenerListaEtiquetaResolverService,
    ComentariosService,
    ObtenerVideoResolverService,
    VideoService,
    ObtenerEntradaResolverService,
    EntradaService,
    ObtenerLibroNoticiasResolverService,
    ObtenerLibroResolverService,
    LibroService,
    JsonAdapterService,
    ObtenerListaBusquedaResolverService,
    ObtenerEntradaZonaResolverService,
    LinkService,
    ObtenerSagaResolverService,
    SagaService,
    ObtenerResenaResolverService,
    UtilService,
    ObtenerLiveTestResolverService,
    TestService,
    ObtenerListaEditorResolverService,
    ObtenerListaEditoralResolverService,
    ObtenerSagaNoticiasResolverService,
    ObtenerSagaMiscelaneosResolverService,
    ObtenerLibroMiscelaneosResolverService,
    AuthGuardService,
    MessageService,
    Globals
];

export const MODULES = [
    BrowserAnimationsModule,
    ContenidoModule,
    RouterModule,
    HttpClientModule,
    HttpModule,
    AppLoadModule,
    FormsModule,
    ToastModule,
    EstaticasModule,
    AngularFontAwesomeModule,
    AuthModule,
    AppRoutingModule];