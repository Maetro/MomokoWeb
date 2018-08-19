
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputTextModule, MultiSelectModule, GrowlModule,  FileUploadModule, DropdownModule,
  ChipsModule, CheckboxModule, DataTableModule, CalendarModule} from 'primeng/primeng';
import { ListaEntradasComponent } from './lista-entradas/lista-entradas.component';


import { QuillModule } from 'ngx-quill'
import { EntradaDetailComponent } from './entrada-detail/entrada-detail.component';
import { FormsModule } from '@angular/forms';
import { QuillEditorComponent } from './entrada-detail/quill-editor/quill-editor.component';
import { EntradaService } from '../../../services/entrada.service';

import { UtilService } from '../../../services/util/util.service';
import { FileUploadService } from '../services/file-upload.service';
import { GeneralDataService } from '../services/general-data.service';
import { GaleriaService } from '../services/galeria.service';


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
    CalendarModule
  ],
  declarations: [ListaEntradasComponent, EntradaDetailComponent, QuillEditorComponent],
  providers: [
    EntradaService, FileUploadService, UtilService, GeneralDataService, GaleriaService
  ],
  entryComponents: [ListaEntradasComponent]
})
export class GestionEntradasModule { }
