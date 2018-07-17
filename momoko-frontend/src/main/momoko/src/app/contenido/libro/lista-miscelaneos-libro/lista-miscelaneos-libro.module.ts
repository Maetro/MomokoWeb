import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComunesModule } from '../../comunes/comunes.module';
import { RouterModule } from '@angular/router';
import { ListaMiscelaneosLibroComponent } from './lista-miscelaneos-libro.component';


@NgModule({
  imports: [
    CommonModule,
    ComunesModule,      
    RouterModule.forChild([
      { path: '', component: ListaMiscelaneosLibroComponent, pathMatch: 'full'}      
    ])
  ],
  declarations: [ListaMiscelaneosLibroComponent]
})
export class ListaNoticiasLibroModule { }
