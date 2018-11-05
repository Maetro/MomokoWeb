import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from '../auth/services/auth-guard.service';
import { ListaEditorialesComponent } from './gestion-editoriales/lista-editoriales/lista-editoriales.component';
import { EntryFormComponent } from './gestion-entradas/entry-form/entry-form.component';
import { EntryListComponent } from './gestion-entradas/entry-list/entry-list.component';
import { EntryResolverService } from './gestion-entradas/entry-resolver.service';
import { FilterFormComponent } from './gestion-filtros/filter-form/filter-form.component';
import { FilterListComponent } from './gestion-filtros/filter-list/filter-list.component';
import { ListaGaleriasComponent } from './gestion-galerias/lista-galerias/lista-galerias.component';
import { ListaGenerosComponent } from './gestion-generos/lista-generos/lista-generos.component';
import { BookFormComponent } from './gestion-libros/book-form/book-form.component';
import { BookListComponent } from './gestion-libros/book-list/book-list.component';
import { ListaRedactoresComponent } from './gestion-redactores/lista-redactores/lista-redactores.component';
import { ListaSagasComponent } from './gestion-sagas/lista-sagas/lista-sagas.component';
import { GestionComponent } from './gestion.component';
import { GeneralDataResolverService } from './services/general-data-resolver';

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
    component: FilterFormComponent
  },
  {
    path: 'editar-filtro/:url',
    canActivate: [AuthGuardService],
    component: FilterFormComponent,
  },
  {
    path: 'lista-libros',
    canActivate: [AuthGuardService],
    component: BookListComponent
  },
  /*{
    path: 'autores',
    loadChildren: './author-administration/author-administration.module#AuthorAdministrationModule',
    canLoad: [AuthGuardService]
  },*/
  {
    path: 'nuevo-libro',
    canActivate: [AuthGuardService],
    component: BookFormComponent
  },
  {
    path: 'editar-libro/:url',
    canActivate: [AuthGuardService],
    component: BookFormComponent,
  },
  {
    path: 'lista-entradas',
    canActivate: [AuthGuardService],
    component: EntryListComponent
  },
  {
    path: 'nueva-entrada',
    canActivate: [AuthGuardService],
    component: EntryFormComponent,
    resolve: {
      generalData: GeneralDataResolverService
    }
  },
  {
    path: 'editar-entrada/:url',
    canActivate: [AuthGuardService],
    component: EntryFormComponent,
    resolve: {
      entrada: EntryResolverService,
      generalData: GeneralDataResolverService
    }
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
