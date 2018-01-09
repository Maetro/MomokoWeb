import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { SuscripcionService } from '../services/suscripcion.service';




@NgModule({
  imports: [
    CommonModule,
    RouterModule
  ],
  declarations: [HeaderComponent, MenuComponent, FooterComponent],
  providers: [SuscripcionService],
  exports: [HeaderComponent, MenuComponent, FooterComponent]
})
export class ContenidoModule { } 
