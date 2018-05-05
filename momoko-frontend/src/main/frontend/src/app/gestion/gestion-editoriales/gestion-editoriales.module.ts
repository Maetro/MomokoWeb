import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  InputTextModule, FileUploadModule, MultiSelectModule, GrowlModule, DataTableModule,
  SharedModule, DropdownModule
} from 'primeng/primeng';

import { LibroService } from './../../services/libro.service';
import { FileUploadService } from './../../services/fileUpload.service';
import { JsonAdapterService } from 'app/util/json-adapter.service';
import { SagaService } from '../../services/saga.service';
import { PickListModule } from 'primeng/components/picklist/picklist';
import { CheckboxModule } from 'primeng/components/checkbox/checkbox';
import { FormsModule } from '@angular/forms';
import { Ng2CompleterModule } from 'ng2-completer';
import { RedactorService } from 'app/services/redactor.service';
import { EditorialService } from 'app/services/editorial.service';
import { ListaEditorialesComponent } from './lista-editoriales/lista-editoriales.component';

@NgModule({
  imports: [
    CommonModule,
    InputTextModule,
    MultiSelectModule,
    GrowlModule,
    FormsModule,
    DropdownModule,
    FileUploadModule,
    DataTableModule,
    CheckboxModule,
    SharedModule,
    PickListModule,
    Ng2CompleterModule,
  ],
  declarations: [ListaEditorialesComponent],
  providers: [EditorialService]
})
export class GestionEditorialesModule { }
