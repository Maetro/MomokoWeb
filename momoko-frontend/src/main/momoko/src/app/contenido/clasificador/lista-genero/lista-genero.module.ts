import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaGeneroComponent } from './lista-genero.component';
import { RouterModule } from '@angular/router';
import { ObtenerListaGeneroResolverService } from '../../../services/resolvers/obtener-lista-genero-resolver.service';
import { ComunesModule } from '../../comunes/comunes.module';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,
    RouterModule.forChild([
      { path: '', component: ListaGeneroComponent, pathMatch: 'full'},
      {
        path: ':numero_pagina',
        component: ListaGeneroComponent,
        resolve: {
          paginaCategoriaResponse: ObtenerListaGeneroResolverService
        }
      }
    ])
  ],
  declarations: [ListaGeneroComponent]
})
export class ListaGeneroModule { }
