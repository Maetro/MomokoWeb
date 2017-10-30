
import { FormsModule } from '@angular/forms';
import { Ng2CompleterModule } from 'ng2-completer';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LibroDetailComponent } from './libro-detail/libro-detail.component';
import { ListaLibrosComponent } from './lista-libros/lista-libros.component';
import { ListaGenerosComponent } from './lista-generos/lista-generos.component';

import { InputTextModule, FileUploadModule, MultiSelectModule, GrowlModule, DataTableModule, SharedModule } from 'primeng/primeng';

import { LibroService } from './../../services/libro.service';
import { FileUploadService } from './../../services/fileUpload.service';
import { GeneroDetailComponent } from './genero-detail/genero-detail.component';
import { JsonAdapterService } from 'app/util/json-adapter.service';

@NgModule({
  imports: [
    CommonModule,
    InputTextModule,
    MultiSelectModule,
    GrowlModule,
    FormsModule,
    FileUploadModule,
    DataTableModule,
    SharedModule,
    Ng2CompleterModule,
  ],
  declarations: [
    LibroDetailComponent,
    ListaLibrosComponent,
    ListaGenerosComponent,
    GeneroDetailComponent
  ],
  providers: [
    LibroService, FileUploadService, JsonAdapterService
  ],
  entryComponents: [ListaLibrosComponent, ListaGenerosComponent]
})
export class GestionLibrosModule { }
