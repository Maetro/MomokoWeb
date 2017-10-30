import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EntradaComponent } from 'app/contenido/entrada/entrada.component';
import { IndexComponent } from 'app/contenido/index/index.component';
import { IndexDataService } from 'app/services/index-data.service';
import { routing } from 'app/app.routing';
import { AnadirEntradaDirective } from 'app/contenido/index/entrada-portada/anadir-entrada.directive';
import { EntradaPortadaNormalImplComponent } from 'app/contenido/index/entrada-portada/entrada-portada-normal.component';
import { AnadirEntradaComponent } from 'app/contenido/index/entrada-portada/anadir-entrada.component';
import { AnadirEntrada2Component } from 'app/contenido/index/entrada-portada/anadir-entrada2.component';
import { InstagramFeedComponent } from 'app/contenido/instagram-feed/instagram-feed.component';
import { LibrosMesSidebarComponent } from 'app/contenido/index/libros-mes-sidebar/libros-mes-sidebar.component';




@NgModule({
  imports: [
    CommonModule,
    routing
  ],
  declarations: [EntradaComponent, IndexComponent, AnadirEntradaDirective, EntradaPortadaNormalImplComponent,
    AnadirEntradaComponent, AnadirEntrada2Component, InstagramFeedComponent, LibrosMesSidebarComponent],
  providers: [IndexDataService],
  entryComponents: [EntradaPortadaNormalImplComponent]
})
export class ContenidoModule { }
