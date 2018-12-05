import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CheckboxModule } from 'primeng/checkbox';
import { BookDataComponent } from './book-data/book-data.component';
import { BookMenuComponent } from './book-menu/book-menu.component';
import { CustomBlockComponent } from './custom-block/custom-block.component';
import { BlockOnlyComponent } from './custom-block/templates/block-only/block-only.component';
import { FourLinksWithContentComponent } from './custom-block/templates/four-links-with-content/four-links-with-content.component';
import { LazyImageComponent } from './lazy-image/lazy-image.component';
import { LazyLoadDirective } from './lazy-image/lazy-load.directive';
import { Libro3dComponent } from './libro3d/libro3d.component';
import { MenuInternoLibroComponent } from './menu-interno-libro/menu-interno-libro.component';
import { MenuInternoSagaComponent } from './menu-interno-saga/menu-interno-saga.component';
import { MobileSidebarComponent } from './mobile-sidebar/mobile-sidebar.component';
import { NotaCircularPequenaComponent } from './nota-circular-pequena/nota-circular-pequena.component';
import { NotaCircularComponent } from './nota-circular/nota-circular.component';
import { RelatedPostsComponent } from './related-posts/related-posts.component';
import { EntradasSidebarComponent } from './sidebar/entradas-sidebar/entradas-sidebar.component';
import { LibrosMesSidebarComponent } from './sidebar/libros-mes-sidebar/libros-mes-sidebar.component';
import { SidebarInstagramComponent } from './sidebar/sidebar-instagram/sidebar-instagram.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SobreAutorComponent } from './sobre-autor/sobre-autor.component';
import { TagCategoriaComponent } from './tag-categoria/tag-categoria.component';
import { TruncatePipe } from './truncate-pipe/truncate.pipe';

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
    BlockOnlyComponent,
    BookDataComponent,
    TruncatePipe,
    BookMenuComponent,
    LazyImageComponent,
    LazyLoadDirective
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
    TruncatePipe,
    BookMenuComponent,
    LazyImageComponent,
    LazyLoadDirective
  ],
  entryComponents: [CustomBlockComponent]
})
export class ComunesModule {}
