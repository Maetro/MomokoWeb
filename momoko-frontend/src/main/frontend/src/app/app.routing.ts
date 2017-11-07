import { GestionLibrosModule } from './gestion/gestion-libros/gestion-libros.module';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { AuthGuardService } from './auth/services/auth-guard.service';
import { AdminComponent } from './admin/admin.component';
import { PageNotFoundComponent } from './error/page-not-found/page-not-found.component';
import { ListaLibrosComponent } from './gestion/gestion-libros/lista-libros/lista-libros.component';
import { ListaGenerosComponent } from './gestion/gestion-libros/lista-generos/lista-generos.component';
import { ListaEntradasComponent } from 'app/gestion/gestion-entradas/lista-entradas/lista-entradas.component';
import { IndexComponent } from 'app/contenido/index/index.component';

const appRoutes: Routes = [
  { path: 'gestion', component: AdminComponent },
  {
    path: 'admin',
     children: [
          {
            path: 'lista-libros',
            component: ListaLibrosComponent
          },
          {
            path: 'lista-entradas',
            component: ListaEntradasComponent
          },
          {
            path: 'lista-generos',
            component: ListaGenerosComponent
          }
        ],
  },
  {
    path: '',
    component: IndexComponent
  },
  { path: '**', component: PageNotFoundComponent },
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes, { enableTracing: true }) // <-- debugging purposes only);
