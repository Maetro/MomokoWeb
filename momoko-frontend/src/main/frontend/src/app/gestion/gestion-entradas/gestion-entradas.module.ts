import { UtilService } from 'app/services/util.service';
import { SimpleTinyComponent } from './editores-texto/simple-tiny.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputTextModule, MultiSelectModule, GrowlModule,  FileUploadModule, DropdownModule,
  ChipsModule, CheckboxModule, DataTableModule} from 'primeng/primeng';
import { ListaEntradasComponent } from 'app/gestion/gestion-entradas/lista-entradas/lista-entradas.component';
import { FileUploadService } from 'app/services/fileUpload.service';
import { EntradaService } from 'app/services/entrada.service';
import { EntradaDetailComponent } from 'app/gestion/gestion-entradas/entrada-detail/entrada-detail.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ResumenEditorComponent } from 'app/gestion/gestion-entradas/editores-texto/resumen-editor.component';
import { AnadirGaleriaDirective } from './anadir-galeria/anadir-galeria.directive';
import { AnadirGaleriaComponent } from 'app/gestion/gestion-entradas/anadir-galeria/anadir-galeria.component';
import { GaleriaFormImplComponent } from 'app/gestion/gestion-entradas/anadir-galeria/galeria-form-impl.component';


@NgModule({
  imports: [
    CommonModule,
    InputTextModule,
    FormsModule,
    GrowlModule,
    ChipsModule,
    DropdownModule,
    FileUploadModule,
    MultiSelectModule,
    DataTableModule,
    CheckboxModule,
    BrowserAnimationsModule
  ],
  declarations: [ListaEntradasComponent, EntradaDetailComponent, SimpleTinyComponent, ResumenEditorComponent,
    AnadirGaleriaComponent, AnadirGaleriaDirective, GaleriaFormImplComponent],
  providers: [
    EntradaService, FileUploadService, UtilService
  ],
  entryComponents: [ListaEntradasComponent, GaleriaFormImplComponent]
})
export class GestionEntradasModule { }
