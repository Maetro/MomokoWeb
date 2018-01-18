import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaBusquedaComponent } from './lista-busqueda.component';
import { ComunesModule } from '../../comunes/comunes.module';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,
    RouterModule.forChild([
      { path: '', component: ListaBusquedaComponent, pathMatch: 'full'}
    ])
  ],
  declarations: [ListaBusquedaComponent]
})
export class ListaBusquedaModule { }
