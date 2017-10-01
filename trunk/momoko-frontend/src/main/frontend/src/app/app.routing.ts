import { GestionLibrosModule } from './contenido/gestion-libros/gestion-libros.module';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { AuthComponent } from './auth/components/auth/auth.component';
import { AuthGuardService } from './auth/services/auth-guard.service';
import { AdminComponent } from './admin/admin.component';
import { PageNotFoundComponent } from './error/page-not-found/page-not-found.component';
import { ListaLibrosComponent } from './contenido/gestion-libros/lista-libros/lista-libros.component';
import { ListaGenerosComponent } from './contenido/gestion-libros/lista-generos/lista-generos.component';

const appRoutes: Routes = [
  {
    path: 'login',
    redirectTo: '/auth',
    pathMatch: 'full'
  },
  {
    path: 'auth',
    component: AuthComponent
  },
  { path: 'gestion', component: AdminComponent },
  {
    path: 'lista-libros',
    component: ListaLibrosComponent
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
