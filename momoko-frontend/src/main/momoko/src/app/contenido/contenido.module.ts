import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { SuscripcionService } from '../services/suscripcion.service';
import { FormsModule } from '@angular/forms';

import { PageSolicitudComponent } from './estaticas/page-solicitud/page-solicitud.component';
import { PageNotFoundComponent } from './estaticas/page-not-found/page-not-found.component';
import { PageCriteriosComponent } from './estaticas/page-criterios/page-criterios.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  declarations: [HeaderComponent, MenuComponent, FooterComponent],
  providers: [SuscripcionService],
  exports: [HeaderComponent, MenuComponent, FooterComponent]
})
export class ContenidoModule { } 
