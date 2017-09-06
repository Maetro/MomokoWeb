import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {AuthComponent} from './auth/components/auth/auth.component';
import {AuthGuardService} from './auth/services/auth-guard.service';
import { AdminComponent } from './admin/admin.component';

import { ListaLibrosComponent } from './contenido/lista-libros/lista-libros.component';
import { PageNotFoundComponent} from './error/page-not-found/page-not-found.component';

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
    { path: 'lista-libros',
      component: ListaLibrosComponent
    },
    { path: '',
      component: ListaLibrosComponent
    },
    { path: '**', component: PageNotFoundComponent },
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes,{ enableTracing: false }) // <-- debugging purposes only);
