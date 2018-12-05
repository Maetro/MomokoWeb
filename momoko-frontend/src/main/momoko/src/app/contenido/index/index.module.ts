import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { YoutubeService } from '../../services/youtube.service';
import { ComunesModule } from '../comunes/comunes.module';
import { CustomBlockIndexDirective } from './custom-block-index/custom-block-index.directive';
import { Fila3entradasfondonegroComponent } from './fila3entradasfondonegro/fila3entradasfondonegro.component';
import { IndexHeaderComponent } from './index-header/index-header.component';
import { IndexComponent } from './index.component';
import { LibrosHorizontalComponent } from './libros-horizontal/libros-horizontal.component';
import { ObtenerIndexDataResolverService } from './resolvers/obtener-index-data-resolver.service';
import { IndexDataService } from './services/index-data.service';
import { VideosHorizontalComponent } from './videos-horizontal/videos-horizontal.component';
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
    IndexHeaderComponent,
    CustomBlockIndexDirective
  ],
  providers: [YoutubeService, ObtenerIndexDataResolverService, IndexDataService],
  entryComponents: []
})
export class IndexModule {}
