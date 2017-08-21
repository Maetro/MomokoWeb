import { AdminComponent } from './admin/admin.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';
import { LibroDetailComponent } from './contenido/libro-detail/libro-detail.component';
import { MenuComponent } from './menu/menu.component';
import { HeaderComponent } from './header/header.component';
import { ContenidoComponent } from './contenido/contenido.component';
import { FooterComponent } from './footer/footer.component';
import { routing } from './app.routing';
import { ListaLibrosComponent } from './contenido/lista-libros/lista-libros.component';
import { PageNotFoundComponent} from './error/page-not-found/page-not-found.component';
import { InputTextModule} from 'primeng/primeng';
import { MultiSelectModule} from 'primeng/primeng';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';


import { LoginFormComponent } from './auth/components/login-form/login-form.component';
import { RegisterFormComponent } from './auth/components/register-form/register-form.component';

@NgModule({
  declarations: [
    AppComponent,
    LibroDetailComponent,
    MenuComponent,
    HeaderComponent,
    ContenidoComponent,
    FooterComponent,
    AdminComponent,
    ListaLibrosComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    AuthModule,
    HttpClientModule,
    InputTextModule,
    MultiSelectModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
