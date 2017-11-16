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
    CrearComentarioComponent, PlantillaComentarioComponent, VideosHorizontalComponent],
  providers: [IndexDataService, ObtenerLibroResolverService, ObtenerAnalisisResolverService, ComentariosService,
    YoutubeService],
  exports: [RouterModule],
  entryComponents: [EntradaPortadaNormalImplComponent]
})
export class ContenidoModule { }
