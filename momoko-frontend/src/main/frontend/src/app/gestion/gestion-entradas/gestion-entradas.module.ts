import { UtilService } from '../../services/util.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputTextModule, MultiSelectModule, GrowlModule,  FileUploadModule, DropdownModule,
  ChipsModule, CheckboxModule, DataTableModule, CalendarModule} from 'primeng/primeng';
import { ListaEntradasComponent } from './lista-entradas/lista-entradas.component';
import { FileUploadService } from '../../services/fileUpload.service';
import { EntradaService } from '../../services/entrada.service';
import { QuillModule } from 'ngx-quill'
import { EntradaDetailComponent } from './entrada-detail/entrada-detail.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { QuillEditorComponent } from './entrada-detail/quill-editor/quill-editor.component';


@NgModule({
  imports: [
    CommonModule,
    InputTextModule,
    FormsModule,
    GrowlModule,
    QuillModule,
    ChipsModule,
    DropdownModule,
    FileUploadModule,
    MultiSelectModule,
    DataTableModule,
    CheckboxModule,
    CalendarModule,
    BrowserAnimationsModule
  ],
  declarations: [ListaEntradasComponent, EntradaDetailComponent, QuillEditorComponent],
  providers: [
    EntradaService, FileUploadService, UtilService
  ],
  entryComponents: [ListaEntradasComponent]
})
export class GestionEntradasModule { }
