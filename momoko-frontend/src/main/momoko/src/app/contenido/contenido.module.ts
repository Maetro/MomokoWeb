import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SidebarMenuComponent } from './sidebar-menu/sidebar-menu.component';
import { JoinUsModule } from './comunes/join-us/join-us.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    JoinUsModule,
  ],
  declarations: [MenuComponent, SidebarMenuComponent, FooterComponent],
  exports: [MenuComponent, SidebarMenuComponent, FooterComponent]
})
export class ContenidoModule { } 
