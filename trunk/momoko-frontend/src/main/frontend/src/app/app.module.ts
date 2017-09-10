
import { AdminComponent } from './admin/admin.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';

import { MenuComponent } from './menu/menu.component';
import { HeaderComponent } from './header/header.component';
import { ContenidoComponent } from './contenido/contenido.component';
import { FooterComponent } from './footer/footer.component';
import { routing } from './app.routing';
import { PageNotFoundComponent } from './error/page-not-found/page-not-found.component';


import { LoginFormComponent } from './auth/components/login-form/login-form.component';
import { RegisterFormComponent } from './auth/components/register-form/register-form.component';

import { GestionLibrosModule } from './contenido/gestion-libros/gestion-libros.module';
import { ListaLibrosComponent } from './contenido/gestion-libros/lista-libros/lista-libros.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    HeaderComponent,
    ContenidoComponent,
    FooterComponent,
    AdminComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AuthModule,
    HttpClientModule,
    HttpModule,
    GestionLibrosModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [ListaLibrosComponent]

})
export class AppModule { }
