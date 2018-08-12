import { MiscelaneosComponent } from './main-content/miscelaneos/miscelaneos.component';
import { ListaGeneroComponent } from './clasificador/lista-genero/lista-genero.component';
import { VideoService } from '../services/video.service';
import { YoutubeService } from '../services/youtube.service';
import { ComentariosService } from '../services/comentarios.service';
import { ObtenerLibroResolverService } from '../services/resolvers/obtener-libro-resolver.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './index/index.component';
import { IndexDataService } from '../services/index-data.service';
import { AnadirEntradaDirective } from './index/entrada-portada/anadir-entrada.directive';
import { EntradaPortadaNormalImplComponent } from './index/entrada-portada/entrada-portada-normal.component';
import { AnadirEntradaComponent } from './index/entrada-portada/anadir-entrada.component';
import { AnadirEntrada2Component } from './index/entrada-portada/anadir-entrada2.component';
import { LibrosMesSidebarComponent } from './index/libros-mes-sidebar/libros-mes-sidebar.component';
import { Fila3entradasfondonegroComponent } from './index/fila3entradasfondonegro/fila3entradasfondonegro.component';
import { LibrosHorizontalComponent } from './index/libros-horizontal/libros-horizontal.component';
import { Libro3dComponent } from './index/libro3d/libro3d.component';
import { FichaLibroComponent } from './ficha-libro/ficha-libro.component';
import { EntradaRoutingModule } from './entrada-routing.module';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { EntradaSimpleHorizontalComponent } from './entrada-simple-horizontal/entrada-simple-horizontal.component';
import { AnalisisComponent } from './analisis/analisis.component';
import { ObtenerAnalisisResolverService } from '../services/resolvers/obtener-analisis-resolver.service';
import { MenuInternoLibroComponent } from './menu-interno-libro/menu-interno-libro.component';
import { NotaCircularComponent } from './nota-circular/nota-circular.component';

import { GrowlModule } from 'primeng/components/growl/growl';
import { ScrollToModule } from 'ng2-scroll-to-el';
import { CrearComentarioComponent } from './analisis/crear-comentario/crear-comentario.component';
import { PlantillaComentarioComponent } from './analisis/plantilla-comentario/plantilla-comentario.component';
import { VideosHorizontalComponent } from './index/videos-horizontal/videos-horizontal.component';
import { ObtenerVideoResolverService } from '../services/resolvers/obtener-video-resolver.service';
import { ObtenerListaGeneroResolverService } from '../services/resolvers/obtener-lista-genero-resolver.service';
import { ClasificadorService } from '../services/clasificador.service';
import { ObtenerDatosGeneralesResolverService } from '../services/resolvers/obtener-datos-generales-resolver.service';
import { MainContentComponent } from './main-content/main-content.component';
import { ListaCategoriaComponent } from './clasificador/lista-categoria/lista-categoria.component';
import { ObtenerListaCategoriaResolverService } from '../services/resolvers/obtener-lista-categoria-resolver.service';
import { NoticiaComponent } from './main-content/noticia/noticia.component';
import { ObtenerIndexDataResolverService } from '../services/resolvers/obtener-index-data-resolver.service';
import { EntradaPortadaVideoComponent } from './index/entrada-portada/entrada-portada-video/entrada-portada-video.component';
import { SatinizeHtmlPipe } from './pipes/satinize-html.pipe';
import { VideosComponent } from './main-content/videos/videos.component';
import { ListaNoticiasLibroComponent } from './lista-noticias-libro/lista-noticias-libro.component';
import { ObtenerLibroNoticiasResolverService } from '../services/resolvers/obtener-libro-noticias-resolver.service';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SidebarInstagramComponent } from './sidebar-instagram/sidebar-instagram.component';
import { TagCategoriaComponent } from './tag-categoria/tag-categoria.component';
import { SobreAutorComponent } from './sobre-autor/sobre-autor.component';
import { ListaEtiquetaComponent } from './clasificador/lista-etiqueta/lista-etiqueta.component';
import { ObtenerListaEtiquetaResolverService } from '../services/resolvers/obtener-etiqueta-resolver.service';

@NgModule({
  imports: [
    CommonModule,
    EntradaRoutingModule,
    GrowlModule,
    FormsModule,
    ScrollToModule
  ],
  declarations: [
    IndexComponent,
    AnadirEntradaDirective,
    EntradaPortadaNormalImplComponent,
    AnadirEntradaComponent,
    AnadirEntrada2Component,
    LibrosMesSidebarComponent,
    Fila3entradasfondonegroComponent,
    LibrosHorizontalComponent,
    Libro3dComponent,
    FichaLibroComponent,
    EntradaSimpleHorizontalComponent,
    AnalisisComponent,
    MenuInternoLibroComponent,
    NotaCircularComponent,
    CrearComentarioComponent,
    PlantillaComentarioComponent,
    VideosHorizontalComponent,
    ListaGeneroComponent,
    MainContentComponent,
    MiscelaneosComponent,
    ListaCategoriaComponent,
    NoticiaComponent,
    EntradaPortadaVideoComponent,
    SatinizeHtmlPipe,
    VideosComponent,
    ListaNoticiasLibroComponent,
    SidebarComponent,
    SidebarInstagramComponent,
    TagCategoriaComponent,
    SobreAutorComponent,
    ListaEtiquetaComponent
  ],
  providers: [
    ObtenerIndexDataResolverService,
    IndexDataService,
    ObtenerLibroResolverService,
    ObtenerAnalisisResolverService,
    ObtenerVideoResolverService,
    ComentariosService,
    YoutubeService,
    VideoService,
    ObtenerListaGeneroResolverService,
    ObtenerListaCategoriaResolverService,
    ClasificadorService,
    ObtenerDatosGeneralesResolverService,
    ObtenerListaEtiquetaResolverService,
    ObtenerLibroNoticiasResolverService,
    SatinizeHtmlPipe
  ],
  exports: [RouterModule],
  entryComponents: [
    EntradaPortadaNormalImplComponent,
    EntradaPortadaVideoComponent
  ]
})
export class ContenidoModule {}
