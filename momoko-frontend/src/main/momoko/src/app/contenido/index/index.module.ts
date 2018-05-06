import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComunesModule } from '../comunes/comunes.module';
import { EntradaPortadaNormalComponent } from './entrada-portada/entrada-portada-normal/entrada-portada-normal.component';
import { AnadirEntradaDirective } from './entrada-portada/anadir-entrada.directive';
import { AnadirEntradaComponent } from './entrada-portada/anadir-entrada/anadir-entrada.component';
import { AnadirEntrada2Component } from './entrada-portada/anadir-entrada2/anadir-entrada2.component';
import { EntradaPortadaVideoComponent } from './entrada-portada/entrada-portada-video/entrada-portada-video.component';
import { IndexComponent } from './index.component';
import { ObtenerIndexDataResolverService } from '../../services/resolvers/obtener-index-data-resolver.service';
import { IndexDataService } from '../../services/index-data.service';
import { RouterModule } from '@angular/router';
import { Fila3entradasfondonegroComponent } from './fila3entradasfondonegro/fila3entradasfondonegro.component';
import { LibrosHorizontalComponent } from './libros-horizontal/libros-horizontal.component';
import { VideosHorizontalComponent } from './videos-horizontal/videos-horizontal.component';
import { YoutubeService } from '../../services/youtube.service';
import { AnadirEntrada3Component } from './entrada-portada/anadir-entrada3/anadir-entrada3.component';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,
    RouterModule.forChild([
      { path: '', component: IndexComponent, pathMatch: 'full'}
    ])
  ],
  declarations: [IndexComponent, EntradaPortadaNormalComponent, AnadirEntradaDirective, AnadirEntradaComponent, 
    AnadirEntrada2Component, EntradaPortadaNormalComponent, EntradaPortadaVideoComponent, Fila3entradasfondonegroComponent, LibrosHorizontalComponent, VideosHorizontalComponent, AnadirEntrada3Component],
  providers:[ IndexDataService, ObtenerIndexDataResolverService, YoutubeService ],
  entryComponents: [EntradaPortadaNormalComponent, EntradaPortadaVideoComponent]
})
export class IndexModule { }
