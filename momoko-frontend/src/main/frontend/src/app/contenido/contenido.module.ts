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
import { SidebarInstagramComponent } from 'app/contenido/sidebar-instagram/sidebar-instagram.component';
import { EntradaSimpleHorizontalComponent } from 'app/contenido/entrada-simple-horizontal/entrada-simple-horizontal.component';
import { AnalisisComponent } from 'app/contenido/analisis/analisis.component';
import { ObtenerAnalisisResolverService } from 'app/services/resolvers/obtener-analisis-resolver.service';
import { MenuInternoLibroComponent } from 'app/contenido/menu-interno-libro/menu-interno-libro.component';
import { NotaCircularComponent } from 'app/contenido/nota-circular/nota-circular.component';



@NgModule({
  imports: [
    CommonModule,
    routing,
    EntradaRoutingModule
  ],
  declarations: [IndexComponent, AnadirEntradaDirective, EntradaPortadaNormalImplComponent,
    AnadirEntradaComponent, AnadirEntrada2Component, LibrosMesSidebarComponent, Fila3entradasfondonegroComponent,
    LibrosHorizontalComponent, Libro3dComponent, FichaLibroComponent, SidebarInstagramComponent,
    EntradaSimpleHorizontalComponent, AnalisisComponent, MenuInternoLibroComponent, NotaCircularComponent],
  providers: [IndexDataService, ObtenerLibroResolverService, ObtenerAnalisisResolverService],
  exports: [RouterModule],
  entryComponents: [EntradaPortadaNormalImplComponent]
})
export class ContenidoModule { }
