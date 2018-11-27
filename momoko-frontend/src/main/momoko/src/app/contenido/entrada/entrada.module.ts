import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
// Import library module
import { NgxJsonLdModule } from '@ngx-lite/json-ld';
import { GrowlModule } from 'primeng/components/growl/growl';
import { BookTemplateModule } from '../comun/book-template/book-template.module';
import { ComunesModule } from '../comunes/comunes.module';
import { EstaticasModule } from '../estaticas/estaticas.module';
import { AnalisisSagaComponent } from './analisis-saga/analisis-saga.component';
import { AnalisisComponent } from './analisis/analisis.component';
import { EntradaComponent } from './entrada.component';
import { EntryCommonModule } from './entry-common/entry-common.module';
import { MiscelaneoSagaComponent } from './miscelaneo-saga/miscelaneo-saga.component';
import { MiscelaneoComponent } from './miscelaneo/miscelaneo.component';
import { NoticiaSagaComponent } from './noticia-saga/noticia-saga.component';
import { NoticiaComponent } from './noticia/noticia.component';
import { SpecialSagaComponent } from './special-saga/special-saga.component';
import { SpecialComponent } from './special/special.component';
import { VideoComponent } from './video/video.component';
import { RedactorInfoModule } from '../comunes/redactor-info/redactor-info.module';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,
    GrowlModule,
    FormsModule,
    BookTemplateModule,
    NgxJsonLdModule,
    EstaticasModule,
    EntryCommonModule,
    RedactorInfoModule,
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
    SpecialComponent,
    SpecialSagaComponent,
    MiscelaneoComponent,
    MiscelaneoSagaComponent,
    
  ]
})
export class EntradaModule {}
