import { DescriptorSagaModule } from '../comunes/descriptor-saga/descriptor-saga.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComunesModule } from '../../comunes/comunes.module';
import { RouterModule } from '@angular/router';
import { ListaNoticiasSagaComponent } from './lista-noticias-saga.component';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,
    DescriptorSagaModule,
    RouterModule.forChild([
      { path: '', component: ListaNoticiasSagaComponent, pathMatch: 'full' }
    ])
  ],
  declarations: [ListaNoticiasSagaComponent]
})
export class ListaNoticiasSagaModule {}
