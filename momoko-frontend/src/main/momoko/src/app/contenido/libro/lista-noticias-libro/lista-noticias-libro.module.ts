import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComunesModule } from '../../comunes/comunes.module';
import { RouterModule } from '@angular/router';
import { ListaNoticiasLibroComponent } from './lista-noticias-libro.component';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,      
    RouterModule.forChild([
      { path: '', component: ListaNoticiasLibroComponent, pathMatch: 'full'}      
    ])
  ],
  declarations: [ListaNoticiasLibroComponent]
})
export class ListaNoticiasLibroModule { }
