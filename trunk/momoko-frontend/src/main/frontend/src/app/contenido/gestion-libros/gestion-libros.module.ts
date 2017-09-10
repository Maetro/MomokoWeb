import { FormsModule } from '@angular/forms';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LibroDetailComponent } from './libro-detail/libro-detail.component';
import { ListaLibrosComponent } from './lista-libros/lista-libros.component';

import { InputTextModule, FileUploadModule, MultiSelectModule, GrowlModule, DataTableModule, SharedModule } from 'primeng/primeng';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { LibroService } from './../../services/libro.service';
import { FileUploadService } from './../../services/fileUpload.service';

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
    BrowserAnimationsModule
  ],
  declarations: [
    LibroDetailComponent,
    ListaLibrosComponent
  ],
  providers: [
    LibroService, FileUploadService
  ],
  entryComponents: [ListaLibrosComponent]
})
export class GestionLibrosModule { }
