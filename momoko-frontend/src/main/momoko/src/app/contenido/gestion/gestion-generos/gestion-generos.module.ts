import { FormsModule } from '@angular/forms';
import { Ng2CompleterModule } from 'ng2-completer';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ListaGenerosComponent } from './lista-generos/lista-generos.component';

import { InputTextModule, FileUploadModule, MultiSelectModule, GrowlModule, DataTableModule,
  SharedModule, DropdownModule } from 'primeng/primeng';

import { GeneroDetailComponent } from './genero-detail/genero-detail.component';

import { PickListModule } from 'primeng/components/picklist/picklist';
import { CheckboxModule } from 'primeng/components/checkbox/checkbox';
import { LibroService } from '../../../services/libro.service';
import { FileUploadService } from '../services/file-upload.service';
import { JsonAdapterService } from '../../../services/util/json-adapter.service';
import { SagaService } from '../../../services/saga.service';


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
  declarations: [
    ListaGenerosComponent,
    GeneroDetailComponent
  ],
  providers: [
    LibroService, FileUploadService, JsonAdapterService, SagaService
  ],
  entryComponents: [ListaGenerosComponent]
})
export class GestionGenerosModule { }
