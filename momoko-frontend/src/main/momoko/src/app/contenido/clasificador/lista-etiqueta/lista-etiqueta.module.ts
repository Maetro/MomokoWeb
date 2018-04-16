import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaEtiquetaComponent } from './lista-etiqueta.component';
import { ComunesModule } from '../../comunes/comunes.module';
import { RouterModule } from '@angular/router';
import { ObtenerListaEtiquetaResolverService } from '../../../services/resolvers/obtener-etiqueta-resolver.service';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,
    RouterModule.forChild([
      { path: '', component: ListaEtiquetaComponent, pathMatch: 'full'},
      {
        path: ':numero_pagina',
        component: ListaEtiquetaComponent,
        resolve: {
          paginaEtiquetaResponse: ObtenerListaEtiquetaResolverService
        }
      }
    ])
  ],
  declarations: [ListaEtiquetaComponent]
})
export class ListaEtiquetaModule { }
