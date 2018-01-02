import { AdminComponent } from './admin/admin.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';

import { MenuComponent } from './menu/menu.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { routing } from './app.routing';
import { PageNotFoundComponent } from './error/page-not-found/page-not-found.component';

import { GestionLibrosModule } from './gestion/gestion-libros/gestion-libros.module';

import { ListaGenerosComponent } from './gestion/gestion-libros/lista-generos/lista-generos.component';
import { GestionEntradasModule } from 'app/gestion/gestion-entradas/gestion-entradas.module';
import { ListaLibrosComponent } from 'app/gestion/gestion-libros/lista-libros/lista-libros.component';
import { ContenidoModule } from 'app/contenido/contenido.module';
import { AppLoadModule } from 'app/app-load/app-load.module';
import { GestionGaleriasModule } from 'app/gestion/gestion-galerias/gestion-galerias.module';
import { Router } from '@angular/router';
import { SuscripcionService } from 'app/services/suscripcion.service';

import { Angulartics2Module } from 'angulartics2';
import { Angulartics2GoogleAnalytics } from 'angulartics2/ga';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    HeaderComponent,
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
    GestionEntradasModule,
    GestionGaleriasModule,
    ContenidoModule,
    routing,
    AppLoadModule,
    Angulartics2Module.forRoot([Angulartics2GoogleAnalytics])
  ],
  providers: [SuscripcionService],
  bootstrap: [AppComponent],
  entryComponents: [ListaLibrosComponent, ListaGenerosComponent]

})
export class AppModule { }
