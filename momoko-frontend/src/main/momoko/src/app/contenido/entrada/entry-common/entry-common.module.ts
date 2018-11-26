import { CommonModule } from "@angular/common";
import { ComunesModule } from "app/contenido/comunes/comunes.module";
import { SatinizeHtmlPipe } from "../satinize-html.pipe";
import { EntryFooterComponent } from "./entry-footer/entry-footer.component";
import { EntryContentComponent, EntryHeaderComponent } from "./entry-header/entry-header.component";
import { EntrySidebarComponent } from "./entry-sidebar/entry-sidebar.component";

@NgModule({
    imports: [
      CommonModule,
      ComunesModule,
    ],
    declarations: [
      EntryHeaderComponent,
      EntryFooterComponent,
      EntrySidebarComponent,
      EntryContentComponent
    ],
    providers: [SatinizeHtmlPipe],
    exports: [EntryHeaderComponent,
        EntryFooterComponent,
        EntrySidebarComponent,
        EntryContentComponent]
  })
  export class EntryCommonModule {}