import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from '../auth/services/auth-guard.service';
import { CreateFilterComponent } from './gestion-filtros/create-filter/create-filter.component';
import { EditFilterComponent } from './gestion-filtros/edit-filter/edit-filter.component';
import { FilterListComponent } from './gestion-filtros/filter-list/filter-list.component';
import { GestionComponent } from './gestion.component';
import { ListaEntradasComponent } from './gestion-entradas/lista-entradas/lista-entradas.component';

const adminRoutes: Routes = [
  {
    path: '',
    component: GestionComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'lista-filtros',
    canActivate: [AuthGuardService],
    component: FilterListComponent
  },
  {
    path: 'nuevo-filtro',
    canActivate: [AuthGuardService],
    component: CreateFilterComponent
  },
  {
    path: 'editar-filtro/:url',
    component: EditFilterComponent,
  },
  {
    path: 'lista-entradas',
    component: ListaEntradasComponent
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(adminRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class GestionRoutingModule { }
