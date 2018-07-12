import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaGeneroComponent } from './lista-genero.component';
import { RouterModule } from '@angular/router';
import { ObtenerListaGeneroResolverService } from '../../../services/resolvers/obtener-lista-genero-resolver.service';
import { ComunesModule } from '../../comunes/comunes.module';
import { GeneroVistaGridComponent } from './genero-vista-grid/genero-vista-grid.component';
import { GeneroVistaListaComponent } from './genero-vista-lista/genero-vista-lista.component';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,
    RouterModule.forChild([
      {
        path: ':numero_pagina',
        component: ListaGeneroComponent,
        resolve: {
          paginaGeneroResponse: ObtenerListaGeneroResolverService
        }
      },
      { path: '', component: ListaGeneroComponent, pathMatch: 'full'},
    ])
  ],
  declarations: [ListaGeneroComponent, GeneroVistaGridComponent, GeneroVistaListaComponent]
})
export class ListaGeneroModule { }
