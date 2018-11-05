
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputTextModule, MultiSelectModule, GrowlModule,  FileUploadModule, DropdownModule,
  ChipsModule, CheckboxModule, DataTableModule, CalendarModule} from 'primeng/primeng';


import { QuillModule} from 'ngx-quill'
import { FormsModule } from '@angular/forms';
import { EntradaService } from '../../../services/entrada.service';

import { UtilService } from '../../../services/util/util.service';
import { FileUploadService } from '../services/file-upload.service';
import { GeneralDataService } from '../services/general-data.service';
import { GaleriaService } from '../services/galeria.service';
import { EntryListComponent } from './entry-list/entry-list.component';
import { TableModule } from 'primeng/table';
import { EntryFormComponent } from './entry-form/entry-form.component';
import { QuillEditorComponent } from './entry-form/quill-editor/quill-editor.component';


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
    TableModule
  ],
  declarations: [QuillEditorComponent, EntryListComponent, EntryFormComponent],
  providers: [
    EntradaService, FileUploadService, UtilService, GeneralDataService, GaleriaService
  ]
})
export class GestionEntradasModule { }
