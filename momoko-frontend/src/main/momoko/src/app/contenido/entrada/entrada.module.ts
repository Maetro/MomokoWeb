import { MiscelaneoSagaComponent } from './miscelaneo-saga/miscelaneo-saga.component';
import { ComunesModule } from '../comunes/comunes.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EntradaComponent } from './entrada.component';
import { NoticiaComponent } from './noticia/noticia.component';
import { AnalisisComponent } from './analisis/analisis.component';
import { VideoComponent } from './video/video.component';
import { MiscelaneoComponent } from './miscelaneo/miscelaneo.component';

import { PlantillaComentarioComponent } from './zona-comentarios/plantilla-comentario/plantilla-comentario.component';
import { ZonaComentariosComponent } from './zona-comentarios/zona-comentarios.component';
import { SatinizeHtmlPipe } from './satinize-html.pipe';
import { RouterModule } from '@angular/router';
import { GrowlModule } from 'primeng/components/growl/growl';
import { FormsModule } from '@angular/forms';

// Import library module
import { NgxJsonLdModule } from '@ngx-lite/json-ld';
import { RedactorInfoModule } from '../comun/redactor-info/redactor-info.module';
import { AnalisisSagaComponent } from './analisis-saga/analisis-saga.component';
import { NoticiaSagaComponent } from './noticia-saga/noticia-saga.component';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,
    GrowlModule,
    FormsModule,
    RedactorInfoModule,
    NgxJsonLdModule,
    RouterModule.forChild([
      { path: '', component: EntradaComponent, pathMatch: 'full' }
    ])
  ],
  declarations: [
    EntradaComponent,
    NoticiaComponent,
    NoticiaSagaComponent,
    AnalisisComponent,
    AnalisisSagaComponent,
    VideoComponent,
    MiscelaneoComponent,
    MiscelaneoSagaComponent,
    ZonaComentariosComponent,
    PlantillaComentarioComponent,
    SatinizeHtmlPipe
  ],
  providers: [SatinizeHtmlPipe]
})
export class EntradaModule {}
