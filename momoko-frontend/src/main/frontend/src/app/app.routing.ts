import { GestionLibrosModule } from './contenido/gestion-libros/gestion-libros.module';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { AuthGuardService } from './auth/services/auth-guard.service';
import { AdminComponent } from './admin/admin.component';
import { PageNotFoundComponent } from './error/page-not-found/page-not-found.component';
import { ListaLibrosComponent } from './contenido/gestion-libros/lista-libros/lista-libros.component';
import { ListaGenerosComponent } from './contenido/gestion-libros/lista-generos/lista-generos.component';
import { ListaEntradasComponent } from 'app/contenido/gestion-entradas/lista-entradas/lista-entradas.component';

const appRoutes: Routes = [
  { path: 'gestion', component: AdminComponent },
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
  },
  {
    path: '',
    component: ListaLibrosComponent
  },
  { path: '**', component: PageNotFoundComponent },
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes, { enableTracing: false }) // <-- debugging purposes only);
