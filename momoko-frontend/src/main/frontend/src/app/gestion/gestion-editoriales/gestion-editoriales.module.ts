import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  InputTextModule,
  FileUploadModule,
  MultiSelectModule,
  GrowlModule,
  DataTableModule,
  SharedModule,
  DropdownModule,
  PaginatorModule
} from 'primeng/primeng';

import { LibroService } from '../../services/libro.service';
import { FileUploadService } from '../../services/fileUpload.service';
import { JsonAdapterService } from '../../util/json-adapter.service';
import { SagaService } from '../../services/saga.service';
import { PickListModule } from 'primeng/components/picklist/picklist';
import { CheckboxModule } from 'primeng/components/checkbox/checkbox';
import { FormsModule } from '@angular/forms';
import { Ng2CompleterModule } from 'ng2-completer';
import { RedactorService } from '../../services/redactor.service';
import { EditorialService } from '../../services/editorial.service';
import { ListaEditorialesComponent } from './lista-editoriales/lista-editoriales.component';
import { EditorialDetailComponent } from './editorial-detail/editorial-detail.component';
import { EditorModule } from 'primeng/editor';
import { InfoAdicionalModule } from '../comun/info-adicional/info-adicional.module';

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
    PaginatorModule,
    InfoAdicionalModule
  ],
  declarations: [ListaEditorialesComponent, EditorialDetailComponent],
  providers: [EditorialService]
})
export class GestionEditorialesModule {}
