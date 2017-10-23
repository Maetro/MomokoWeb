import { SimpleTinyComponent } from './simple-tiny/simple-tiny.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputTextModule, MultiSelectModule, GrowlModule,  FileUploadModule, DropdownModule,
  ChipsModule, CheckboxModule} from 'primeng/primeng';
import { ListaEntradasComponent } from 'app/contenido/gestion-entradas/lista-entradas/lista-entradas.component';
import { FileUploadService } from 'app/services/fileUpload.service';
import { EntradaService } from 'app/services/entrada.service';
import { EntradaDetailComponent } from 'app/contenido/gestion-entradas/entrada-detail/entrada-detail.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ResumenEditorComponent } from 'app/contenido/gestion-entradas/simple-tiny/resumen-editor.component';


@NgModule({
  imports: [
    CommonModule,
    CommonModule,
    InputTextModule,
    FormsModule,
    GrowlModule,
    ChipsModule,
    DropdownModule,
    FileUploadModule,
    MultiSelectModule,
    CheckboxModule,
    BrowserAnimationsModule
  ],
  declarations: [ListaEntradasComponent, EntradaDetailComponent, SimpleTinyComponent, ResumenEditorComponent ],
  providers: [
    EntradaService, FileUploadService
  ],
  entryComponents: [ListaEntradasComponent]
})
export class GestionEntradasModule { }
