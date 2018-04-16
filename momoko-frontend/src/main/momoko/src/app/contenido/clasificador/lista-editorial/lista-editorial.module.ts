import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaEditorialComponent } from './lista-editorial.component';
import { PaginationModule } from '../pagination/pagination.module';
import { RouterModule } from '@angular/router';
import { ObtenerListaEditoralResolverService } from '../../../services/resolvers/obtener-lista-editoral-resolver.service';
import { ComunesModule } from '../../comunes/comunes.module';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,
    PaginationModule,
    RouterModule.forChild([
      { path: '', component: ListaEditorialComponent, pathMatch: 'full'},
      {
        path: ':numero_pagina',
        component: ListaEditorialComponent,
        resolve: {
          editorial: ObtenerListaEditoralResolverService
        }
      }
    ])
  ],
  declarations: [ListaEditorialComponent]
})
export class ListaEditorialModule { }
