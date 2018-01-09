import { ListaCategoriaComponent } from './lista-categoria.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ObtenerListaCategoriaResolverService } from '../../../services/resolvers/obtener-lista-categoria-resolver.service';
import { ClasificadorService } from '../../../services/clasificador.service';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: '', component: ListaCategoriaComponent, pathMatch: 'full'},
      {
        path: ':numero_pagina',
        component: ListaCategoriaComponent,
        resolve: {
          paginaCategoriaResponse: ObtenerListaCategoriaResolverService
        }
      }
    ])
  ],
  declarations: [ListaCategoriaComponent]
})
export class ListaCategoriaModule { }
