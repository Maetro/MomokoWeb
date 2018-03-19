
import { FormsModule } from '@angular/forms';
import { Ng2CompleterModule } from 'ng2-completer';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LibroDetailComponent } from './libro-detail/libro-detail.component';
import { ListaLibrosComponent } from './lista-libros/lista-libros.component';
import { ListaGenerosComponent } from './lista-generos/lista-generos.component';

import { InputTextModule, FileUploadModule, MultiSelectModule, GrowlModule, DataTableModule,
  SharedModule, DropdownModule } from 'primeng/primeng';

import { LibroService } from './../../services/libro.service';
import { FileUploadService } from './../../services/fileUpload.service';
import { GeneroDetailComponent } from './genero-detail/genero-detail.component';
import { JsonAdapterService } from 'app/util/json-adapter.service';
import { ListaSagasComponent } from './lista-sagas/lista-sagas.component';
import { SagaService } from '../../services/saga.service';
import { SagaDetailComponent } from './saga-detail/saga-detail.component';
import { PickListModule } from 'primeng/components/picklist/picklist';
import { CheckboxModule } from 'primeng/components/checkbox/checkbox';

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
    LibroDetailComponent,
    ListaLibrosComponent,
    ListaGenerosComponent,
    GeneroDetailComponent,
    ListaSagasComponent,
    SagaDetailComponent
  ],
  providers: [
    LibroService, FileUploadService, JsonAdapterService, SagaService
  ],
  entryComponents: [ListaLibrosComponent, ListaGenerosComponent]
})
export class GestionLibrosModule { }
