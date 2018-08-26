
import { FormsModule } from '@angular/forms';
import { Ng2CompleterModule } from 'ng2-completer';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LibroDetailComponent } from './libro-detail/libro-detail.component';
import { ListaLibrosComponent } from './lista-libros/lista-libros.component';

import { InputTextModule, FileUploadModule, MultiSelectModule, GrowlModule, DataTableModule,
  SharedModule, DropdownModule, MessageService } from 'primeng/primeng';

import { PickListModule } from 'primeng/components/picklist/picklist';
import { CheckboxModule } from 'primeng/components/checkbox/checkbox';
import {AccordionModule} from 'primeng/accordion';
import { LibroService } from '../../../services/libro.service';
import { FileUploadService } from '../services/file-upload.service';
import { JsonAdapterService } from '../../../services/util/json-adapter.service';
import { SagaService } from '../../../services/saga.service';
import { BookListComponent } from './book-list/book-list.component';
import { RadioButtonModule } from 'primeng/radiobutton';
import { BookService } from './book.service';
import { TableModule } from 'primeng/table';
import { BookFormComponent } from './book-form/book-form.component';

@NgModule({
  imports: [
    CommonModule,
    InputTextModule,
    MultiSelectModule,
    GrowlModule,
    FormsModule,
    DropdownModule,
    RadioButtonModule,
    AccordionModule,
    FileUploadModule,
    DataTableModule,
    CheckboxModule,
    SharedModule,
    PickListModule,
    Ng2CompleterModule,
    TableModule
  ],
  declarations: [
    LibroDetailComponent,
    ListaLibrosComponent,
    BookListComponent,
    BookFormComponent
  ],
  providers: [
    LibroService, FileUploadService, JsonAdapterService, SagaService, BookService
  ],
  entryComponents: [ListaLibrosComponent]
})
export class GestionLibrosModule { }