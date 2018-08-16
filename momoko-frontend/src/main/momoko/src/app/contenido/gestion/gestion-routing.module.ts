import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from '../auth/services/auth-guard.service';
import { CreateFilterComponent } from './gestion-filtros/create-filter/create-filter.component';
import { EditFilterComponent } from './gestion-filtros/edit-filter/edit-filter.component';
import { FilterListComponent } from './gestion-filtros/filter-list/filter-list.component';
import { GestionComponent } from './gestion.component';

const adminRoutes: Routes = [
  {
    path: '',
    component: GestionComponent,
    canActivate: [AuthGuardService],
    children: [
      {
        path: '',
        canActivateChild: [AuthGuardService],
        children: [
          { path: '', component: GestionComponent }
        ]
      },
      {
        path: 'filter',
        canActivateChild: [AuthGuardService],
        children: [
          { path: '', component: FilterListComponent},
          { path: 'list', redirectTo: '/gestion/filter'},
          { path: 'nuevo-filtro', component: CreateFilterComponent},
          { path: 'editar-filtro/:url', component: EditFilterComponent}
        ]
      }
    ]
  }
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
