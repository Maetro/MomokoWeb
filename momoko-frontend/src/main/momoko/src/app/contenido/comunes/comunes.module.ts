import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TagCategoriaComponent } from './tag-categoria/tag-categoria.component';
import { RouterModule } from '@angular/router';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SidebarInstagramComponent } from './sidebar/sidebar-instagram/sidebar-instagram.component';
import { LibrosMesSidebarComponent } from './sidebar/libros-mes-sidebar/libros-mes-sidebar.component';
import { Libro3dComponent } from './libro3d/libro3d.component';
import { MenuInternoLibroComponent } from './menu-interno-libro/menu-interno-libro.component';
import { SobreAutorComponent } from './sobre-autor/sobre-autor.component';
import { NotaCircularComponent } from './nota-circular/nota-circular.component';
import { FormsModule } from '@angular/forms';
import { MenuInternoSagaComponent } from './menu-interno-saga/menu-interno-saga.component';
import { EntradasSidebarComponent } from './sidebar/entradas-sidebar/entradas-sidebar.component';
import { MobileSidebarComponent } from './mobile-sidebar/mobile-sidebar.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
  declarations: [
    TagCategoriaComponent,
    SidebarComponent,
    SidebarInstagramComponent,
    LibrosMesSidebarComponent,
    Libro3dComponent,
    MenuInternoLibroComponent,
    SobreAutorComponent,
    NotaCircularComponent,
    MenuInternoSagaComponent,
    EntradasSidebarComponent,
    MobileSidebarComponent
  ],
  exports: [
    TagCategoriaComponent,
    SidebarComponent,
    Libro3dComponent,
    MenuInternoLibroComponent,
    SobreAutorComponent,
    NotaCircularComponent,
    MenuInternoSagaComponent,
    MobileSidebarComponent
  ]
})
export class ComunesModule {}
