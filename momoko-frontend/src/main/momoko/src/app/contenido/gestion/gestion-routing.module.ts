import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from '../auth/services/auth-guard.service';
import { CreateFilterComponent } from './gestion-filtros/create-filter/create-filter.component';
import { EditFilterComponent } from './gestion-filtros/edit-filter/edit-filter.component';
import { FilterListComponent } from './gestion-filtros/filter-list/filter-list.component';
import { GestionComponent } from './gestion.component';
import { ListaEntradasComponent } from './gestion-entradas/lista-entradas/lista-entradas.component';
import { ListaSagasComponent } from './gestion-sagas/lista-sagas/lista-sagas.component';
import { ListaGenerosComponent } from './gestion-generos/lista-generos/lista-generos.component';
import { ListaLibrosComponent } from './gestion-libros/lista-libros/lista-libros.component';
import { ListaGaleriasComponent } from './gestion-galerias/lista-galerias/lista-galerias.component';
import { ListaEditorialesComponent } from './gestion-editoriales/lista-editoriales/lista-editoriales.component';
import { ListaRedactoresComponent } from './gestion-redactores/lista-redactores/lista-redactores.component';

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
    canActivate: [AuthGuardService],
    component: EditFilterComponent,
  },
  {
    path: 'lista-entradas',
    canActivate: [AuthGuardService],
    component: ListaEntradasComponent
  },
  {
    path: 'lista-libros',
    canActivate: [AuthGuardService],
    component: ListaLibrosComponent
  },
  {
    path: 'lista-sagas',
    canActivate: [AuthGuardService],
    component: ListaSagasComponent
  },
  {
    path: 'lista-generos',
    canActivate: [AuthGuardService],
    component: ListaGenerosComponent
  },
  {
    path: 'lista-galerias',
    component: ListaGaleriasComponent
  },
  {
    path: 'lista-redactores',
    component: ListaRedactoresComponent
  },
  {
    path: 'lista-editoriales',
    component: ListaEditorialesComponent
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
