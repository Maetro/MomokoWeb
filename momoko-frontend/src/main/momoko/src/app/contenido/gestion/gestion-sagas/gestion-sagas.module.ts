import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaSagasComponent } from './lista-sagas/lista-sagas.component';
import { SagaDetailComponent } from './saga-detail/saga-detail.component';
import { FileUploadService } from '../services/file-upload.service';
import { JsonAdapterService } from '../../../services/util/json-adapter.service';
import { SagaService } from '../../../services/saga.service';
import { InputTextModule, MultiSelectModule, GrowlModule, DropdownModule, FileUploadModule, DataTableModule, CheckboxModule, SharedModule, PickListModule } from 'primeng/primeng';
import { FormsModule } from '@angular/forms';
import { Ng2CompleterModule } from 'ng2-completer';
import { LibroService } from '../../../services/libro.service';


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
  providers: [
    FileUploadService, JsonAdapterService, SagaService, LibroService
  ],
  declarations: [ListaSagasComponent, SagaDetailComponent]
})
export class GestionSagasModule { }
