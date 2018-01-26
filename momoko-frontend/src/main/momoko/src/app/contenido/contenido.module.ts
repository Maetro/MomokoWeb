import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { SuscripcionService } from '../services/suscripcion.service';
import { FormsModule } from '@angular/forms';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PageSolicitudComponent } from './page-solicitud/page-solicitud.component';




@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  declarations: [HeaderComponent, MenuComponent, FooterComponent, PageNotFoundComponent, PageSolicitudComponent],
  providers: [SuscripcionService],
  exports: [HeaderComponent, MenuComponent, FooterComponent]
})
export class ContenidoModule { } 
