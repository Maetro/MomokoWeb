import { ComunesModule } from './../comunes/comunes.module';
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

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,
    GrowlModule,
    FormsModule,      
    RouterModule.forChild([
      { path: '', component: EntradaComponent, pathMatch: 'full'}      
    ])
  ],
  declarations: [EntradaComponent, NoticiaComponent, AnalisisComponent, VideoComponent, MiscelaneoComponent, ZonaComentariosComponent, PlantillaComentarioComponent,
    SatinizeHtmlPipe],
    providers:[SatinizeHtmlPipe]
})
export class EntradaModule { }
