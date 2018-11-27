import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { BookTemplateModule } from "app/contenido/comun/book-template/book-template.module";
import { ComunesModule } from "app/contenido/comunes/comunes.module";
import { RedactorInfoModule } from "app/contenido/comunes/redactor-info/redactor-info.module";
import { EntryContentComponent } from "./entry-content/entry-content.component";
import { EntryFooterComponent } from "./entry-footer/entry-footer.component";
import { EntryHeaderComponent } from "./entry-header/entry-header.component";
import { EntrySidebarComponent } from "./entry-sidebar/entry-sidebar.component";
import { SatinizeHtmlPipe } from "./satinize-html.pipe";
import { PlantillaComentarioComponent } from "./zona-comentarios/plantilla-comentario/plantilla-comentario.component";
import { ZonaComentariosComponent } from "./zona-comentarios/zona-comentarios.component";

@NgModule({
    imports: [
      CommonModule,
      ComunesModule,
      RedactorInfoModule,
      BookTemplateModule,
      FormsModule,
      ReactiveFormsModule,
      RouterModule
    ],
    declarations: [
      EntryHeaderComponent,
      EntryFooterComponent,
      EntrySidebarComponent,
      EntryContentComponent,
      ZonaComentariosComponent,
      PlantillaComentarioComponent,
      SatinizeHtmlPipe,
      
    ],
    providers: [SatinizeHtmlPipe],
    exports: [
        EntryHeaderComponent,
        EntryFooterComponent,
        EntrySidebarComponent,
        EntryContentComponent,
        ZonaComentariosComponent,
        PlantillaComentarioComponent,
        SatinizeHtmlPipe]
  })
  export class EntryCommonModule {}