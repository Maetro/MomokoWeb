import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaEditorialComponent } from './lista-editorial.component';
import { PaginationModule } from '../pagination/pagination.module';
import { RouterModule } from '@angular/router';
import { ObtenerListaEditoralResolverService } from '../../../services/resolvers/obtener-lista-editoral-resolver.service';

@NgModule({
  imports: [
    CommonModule,
    PaginationModule,
    RouterModule.forChild([
      { path: '', component: ListaEditorialComponent, pathMatch: 'full'},
      {
        path: ':numero_pagina',
        component: ListaEditorialComponent,
        resolve: {
          redactor: ObtenerListaEditoralResolverService
        }
      }
    ])
  ],
  declarations: [ListaEditorialComponent]
})
export class ListaEditorialModule { }
