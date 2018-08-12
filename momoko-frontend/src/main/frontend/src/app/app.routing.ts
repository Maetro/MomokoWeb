import { GestionLibrosModule } from './gestion/gestion-libros/gestion-libros.module';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { AuthGuardService } from './auth/services/auth-guard.service';
import { AdminComponent } from './admin/admin.component';
import { PageNotFoundComponent } from './error/page-not-found/page-not-found.component';
import { ListaLibrosComponent } from './gestion/gestion-libros/lista-libros/lista-libros.component';
import { ListaGenerosComponent } from './gestion/gestion-libros/lista-generos/lista-generos.component';
import { ListaEntradasComponent } from './gestion/gestion-entradas/lista-entradas/lista-entradas.component';
import { IndexComponent } from './contenido/index/index.component';
import { ListaGaleriasComponent } from './gestion/gestion-galerias/lista-galerias/lista-galerias.component';
import { ObtenerIndexDataResolverService } from './services/resolvers/obtener-index-data-resolver.service';
import { ListaSagasComponent } from './gestion/gestion-libros/lista-sagas/lista-sagas.component';
import { ListaRedactoresComponent } from './gestion/gestion-redactores/lista-redactores/lista-redactores.component';
import { ListaEditorialesComponent } from './gestion/gestion-editoriales/lista-editoriales/lista-editoriales.component';
import { ListaUrlsComponent } from './gestion/gestion-urls/lista-urls/lista-urls.component';
import { FilterListComponent } from './gestion/gestion-filtros/filter-list/filter-list.component';
import { CreateFilterComponent } from './gestion/gestion-filtros/create-filter/create-filter.component';
import { EditFilterComponent } from './gestion/gestion-filtros/edit-filter/edit-filter.component';

const appRoutes: Routes = [
  { path: 'gestion', component: AdminComponent },
  {
    path: 'admin',
    children: [
      {
        path: 'lista-filtros',
        component: FilterListComponent,
      },
      {
        path: 'nuevo-filtro',
        component: CreateFilterComponent,
      },
      {
        path: 'editar-filtro/:url',
        component: EditFilterComponent,
      },
      {
        path: 'lista-libros',
        component: ListaLibrosComponent
      },
      {
        path: 'lista-entradas',
        component: ListaEntradasComponent
      },
      {
        path: 'lista-sagas',
        component: ListaSagasComponent
      },
      {
        path: 'lista-generos',
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
      {
        path: 'gestor-urls',
        component: ListaUrlsComponent
      }
    ]
  },
  {
    path: '',
    component: IndexComponent,
    resolve: {
      obtenerIndexDataResponse: ObtenerIndexDataResolverService
    }
  },
  { path: '**', component: PageNotFoundComponent }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes, {
  enableTracing: false
}); // <-- debugging purposes only);
