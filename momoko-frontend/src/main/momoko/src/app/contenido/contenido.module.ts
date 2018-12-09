import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SidebarMenuComponent } from './sidebar-menu/sidebar-menu.component';
import { JoinUsModule } from './comunes/join-us/join-us.module';
import { FooterModule } from 'app/shared/footer/footer.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    FooterModule,
    ReactiveFormsModule,
    JoinUsModule,
  ],
  declarations: [MenuComponent, SidebarMenuComponent],
  exports: [MenuComponent, SidebarMenuComponent]
})
export class ContenidoModule { } 
