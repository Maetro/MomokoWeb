import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ListaEditorComponent } from './lista-editor.component';
import { ObtenerListaEditorResolverService } from '../../../services/resolvers/obtener-lista-editor-resolver.service';
import { PaginationModule } from '../pagination/pagination.module';
import { RedactorInfoModule } from 'app/contenido/comunes/redactor-info/redactor-info.module';

@NgModule({
  imports: [
    CommonModule,
    PaginationModule,
    RedactorInfoModule,
    RouterModule.forChild([
      { path: '', component: ListaEditorComponent, pathMatch: 'full'},
      {
        path: ':numero_pagina',
        component: ListaEditorComponent,
        resolve: {
          redactor: ObtenerListaEditorResolverService
        }
      }
    ])
  ],
  declarations: [ListaEditorComponent]
})
export class ListaEditorModule { }
