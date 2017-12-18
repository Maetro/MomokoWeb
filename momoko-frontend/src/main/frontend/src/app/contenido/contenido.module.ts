import { MiscelaneosComponent } from './main-content/miscelaneos/miscelaneos.component';
import { ListaGeneroComponent } from 'app/contenido/clasificador/lista-genero/lista-genero.component';
import { VideoService } from 'app/services/video.service';
import { YoutubeService } from './../services/youtube.service';
import { ComentariosService } from 'app/services/comentarios.service';
import { ObtenerLibroResolverService } from './../services/resolvers/obtener-libro-resolver.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from 'app/contenido/index/index.component';
import { IndexDataService } from 'app/services/index-data.service';
import { routing } from 'app/app.routing';
import { AnadirEntradaDirective } from 'app/contenido/index/entrada-portada/anadir-entrada.directive';
import { EntradaPortadaNormalImplComponent } from 'app/contenido/index/entrada-portada/entrada-portada-normal.component';
import { AnadirEntradaComponent } from 'app/contenido/index/entrada-portada/anadir-entrada.component';
import { AnadirEntrada2Component } from 'app/contenido/index/entrada-portada/anadir-entrada2.component';
import { LibrosMesSidebarComponent } from 'app/contenido/index/libros-mes-sidebar/libros-mes-sidebar.component';
import { Fila3entradasfondonegroComponent } from 'app/contenido/index/fila3entradasfondonegro/fila3entradasfondonegro.component';
import { LibrosHorizontalComponent } from 'app/contenido/index/libros-horizontal/libros-horizontal.component';
import { Libro3dComponent } from 'app/contenido/index/libro3d/libro3d.component';
import { FichaLibroComponent } from 'app/contenido/ficha-libro/ficha-libro.component';
import { EntradaRoutingModule } from 'app/contenido/entrada-routing.module';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SidebarInstagramComponent } from 'app/contenido/sidebar-instagram/sidebar-instagram.component';
import { EntradaSimpleHorizontalComponent } from 'app/contenido/entrada-simple-horizontal/entrada-simple-horizontal.component';
import { AnalisisComponent } from 'app/contenido/analisis/analisis.component';
import { ObtenerAnalisisResolverService } from 'app/services/resolvers/obtener-analisis-resolver.service';
import { MenuInternoLibroComponent } from 'app/contenido/menu-interno-libro/menu-interno-libro.component';
import { NotaCircularComponent } from 'app/contenido/nota-circular/nota-circular.component';

import { GrowlModule } from 'primeng/components/growl/growl';
import { ScrollToModule } from 'ng2-scroll-to-el';
import { CrearComentarioComponent } from 'app/contenido/analisis/crear-comentario/crear-comentario.component';
import { PlantillaComentarioComponent } from 'app/contenido/analisis/plantilla-comentario/plantilla-comentario.component';
import { VideosHorizontalComponent } from 'app/contenido/index/videos-horizontal/videos-horizontal.component';
import { ObtenerVideoResolverService } from 'app/services/resolvers/obtener-video-resolver.service';
import { ObtenerListaGeneroResolverService } from 'app/services/resolvers/obtener-lista-genero-resolver.service';
import { ClasificadorService } from 'app/services/clasificador.service';
import { ObtenerDatosGeneralesResolverService } from 'app/services/resolvers/obtener-datos-generales-resolver.service';
import { MainContentComponent } from 'app/contenido/main-content/main-content.component';
import { ListaCategoriaComponent } from 'app/contenido/clasificador/lista-categoria/lista-categoria.component';
import { ObtenerListaCategoriaResolverService } from 'app/services/resolvers/obtener-lista-categoria-resolver.service';
import { NoticiaComponent } from 'app/contenido/main-content/noticia/noticia.component';
import { ObtenerIndexDataResolverService } from 'app/services/resolvers/obtener-index-data-resolver.service';
import { EntradaPortadaVideoComponent } from 'app/contenido/index/entrada-portada/entrada-portada-video/entrada-portada-video.component';
import { SatinizeHtmlPipe } from './pipes/satinize-html.pipe';
import { VideosComponent } from 'app/contenido/main-content/videos/videos.component';




@NgModule({
  imports: [
    CommonModule,
    routing,
    EntradaRoutingModule,
    GrowlModule,
    FormsModule,
    ScrollToModule
  ],
  declarations: [IndexComponent, AnadirEntradaDirective, EntradaPortadaNormalImplComponent,
    AnadirEntradaComponent, AnadirEntrada2Component, LibrosMesSidebarComponent, Fila3entradasfondonegroComponent,
    LibrosHorizontalComponent, Libro3dComponent, FichaLibroComponent, SidebarInstagramComponent,
    EntradaSimpleHorizontalComponent, AnalisisComponent, MenuInternoLibroComponent, NotaCircularComponent,
    CrearComentarioComponent, PlantillaComentarioComponent, VideosHorizontalComponent, ListaGeneroComponent, MainContentComponent,
     MiscelaneosComponent, ListaCategoriaComponent, NoticiaComponent, EntradaPortadaVideoComponent, SatinizeHtmlPipe, VideosComponent],
   providers: [ObtenerIndexDataResolverService, IndexDataService, ObtenerLibroResolverService, ObtenerAnalisisResolverService,
    ObtenerVideoResolverService, ComentariosService, YoutubeService, VideoService, ObtenerListaGeneroResolverService,
    ObtenerListaCategoriaResolverService, ClasificadorService,  ObtenerDatosGeneralesResolverService, SatinizeHtmlPipe],
  exports: [RouterModule],
  entryComponents: [EntradaPortadaNormalImplComponent, EntradaPortadaVideoComponent]
})
export class ContenidoModule { }
