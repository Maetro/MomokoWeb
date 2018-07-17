import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { SuscripcionService } from '../services/suscripcion.service';
import { FormsModule } from '@angular/forms';

import { SidebarMenuComponent } from './sidebar-menu/sidebar-menu.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  declarations: [HeaderComponent, MenuComponent, SidebarMenuComponent, FooterComponent],
  providers: [SuscripcionService],
  exports: [HeaderComponent, MenuComponent, SidebarMenuComponent, FooterComponent]
})
export class ContenidoModule { } 
