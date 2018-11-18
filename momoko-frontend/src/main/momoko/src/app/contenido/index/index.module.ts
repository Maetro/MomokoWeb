import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComunesModule } from '../comunes/comunes.module';
import { IndexComponent } from './index.component';
import { ObtenerIndexDataResolverService } from './resolvers/obtener-index-data-resolver.service';
import { IndexDataService } from './services/index-data.service';
import { RouterModule } from '@angular/router';
import { Fila3entradasfondonegroComponent } from './fila3entradasfondonegro/fila3entradasfondonegro.component';
import { LibrosHorizontalComponent } from './libros-horizontal/libros-horizontal.component';
import { VideosHorizontalComponent } from './videos-horizontal/videos-horizontal.component';
import { YoutubeService } from '../../services/youtube.service';
import { IndexHeaderComponent } from './index-header/index-header.component';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,
    RouterModule.forChild([
      { path: '', 
      component: IndexComponent,
      pathMatch: 'full',
      resolve: {
        indexDataResponse: ObtenerIndexDataResolverService
      }
    }
    ])
  ],
  declarations: [
    IndexComponent,
    Fila3entradasfondonegroComponent,
    LibrosHorizontalComponent,
    VideosHorizontalComponent,
    IndexHeaderComponent
  ],
  providers: [YoutubeService, ObtenerIndexDataResolverService, IndexDataService]
})
export class IndexModule {}
