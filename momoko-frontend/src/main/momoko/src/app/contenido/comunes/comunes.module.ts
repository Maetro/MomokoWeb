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
import { TruncatePipe } from './truncate-pipe/truncate.pipe';
import { NotaCircularPequenaComponent } from './nota-circular-pequena/nota-circular-pequena.component';
import { CheckboxModule } from 'primeng/checkbox';
import { JoinUsModule } from './join-us/join-us.module';
import { RelatedPostsComponent } from './related-posts/related-posts.component';
import { CustomBlockComponent } from './custom-block/custom-block.component';
import { FourLinksWithContentComponent } from './custom-block/templates/four-links-with-content/four-links-with-content.component';
import { BookDataComponent } from './book-data/book-data.component';

@NgModule({
  imports: [CommonModule, RouterModule, CheckboxModule, FormsModule],
  declarations: [
    TagCategoriaComponent,
    SidebarComponent,
    SidebarInstagramComponent,
    LibrosMesSidebarComponent,
    Libro3dComponent,
    MenuInternoLibroComponent,
    SobreAutorComponent,
    NotaCircularComponent,
    NotaCircularPequenaComponent,
    MenuInternoSagaComponent,
    EntradasSidebarComponent,
    MobileSidebarComponent,
    RelatedPostsComponent,
    CustomBlockComponent,
    FourLinksWithContentComponent,
    BookDataComponent,
    TruncatePipe
  ],
  exports: [
    TagCategoriaComponent,
    SidebarComponent,
    Libro3dComponent,
    MenuInternoLibroComponent,
    SobreAutorComponent,
    NotaCircularComponent,
    NotaCircularPequenaComponent,
    MenuInternoSagaComponent,
    MobileSidebarComponent,
    RelatedPostsComponent,
    CustomBlockComponent,
    BookDataComponent,
    TruncatePipe
  ],
  entryComponents: [CustomBlockComponent]
})
export class ComunesModule {}
