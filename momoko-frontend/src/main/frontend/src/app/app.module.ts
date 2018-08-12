import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { Angulartics2Module } from 'angulartics2';
import { Angulartics2GoogleAnalytics } from 'angulartics2/ga';
import { AdminComponent } from './admin/admin.component';
import { AppLoadModule } from './app-load/app-load.module';
import { AppComponent } from './app.component';
import { routing } from './app.routing';
import { AuthModule } from './auth/auth.module';
import { ContenidoModule } from './contenido/contenido.module';
import { PageNotFoundComponent } from './error/page-not-found/page-not-found.component';
import { FooterComponent } from './footer/footer.component';
import { GestionEditorialesModule } from './gestion/gestion-editoriales/gestion-editoriales.module';
import { GestionEntradasModule } from './gestion/gestion-entradas/gestion-entradas.module';
import { FilterAdministrationModule } from './gestion/gestion-filtros/gestion-filtros.module';
import { GestionGaleriasModule } from './gestion/gestion-galerias/gestion-galerias.module';
import { GestionLibrosModule } from './gestion/gestion-libros/gestion-libros.module';
import { ListaGenerosComponent } from './gestion/gestion-libros/lista-generos/lista-generos.component';
import { ListaLibrosComponent } from './gestion/gestion-libros/lista-libros/lista-libros.component';
import { GestionRedactoresModule } from './gestion/gestion-redactores/gestion-redactores.module';
import { GestionUrlsModule } from './gestion/gestion-urls/gestion-urls.module';
import { HeaderComponent } from './header/header.component';
import { MenuComponent } from './menu/menu.component';
import { SuscripcionService } from './services/suscripcion.service';







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
    GestionRedactoresModule,
    GestionEditorialesModule,
    FilterAdministrationModule,
    GestionUrlsModule,
    ContenidoModule,
    routing,
    AppLoadModule,
    Angulartics2Module.forRoot([Angulartics2GoogleAnalytics])
  ],
  providers: [SuscripcionService],
  bootstrap: [AppComponent],
  entryComponents: [ListaLibrosComponent, ListaGenerosComponent]
})
export class AppModule {}
