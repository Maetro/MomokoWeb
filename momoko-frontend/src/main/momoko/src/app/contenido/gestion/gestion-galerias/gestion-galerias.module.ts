import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaGaleriasComponent } from './lista-galerias/lista-galerias.component';
import { GaleriaDetailComponent } from './galeria-detail/galeria-detail.component';
// tslint:disable-next-line:max-line-length
import { InputTextModule, MultiSelectModule, GrowlModule, DropdownModule, FileUploadModule, DataTableModule, SharedModule } from 'primeng/primeng';
import { FormsModule } from '@angular/forms';
import { Ng2CompleterModule } from 'ng2-completer';
import { GaleriaService } from '../services/galeria.service';
import { FileUploadService } from '../services/file-upload.service';
import { JsonAdapterService } from '../../../services/util/json-adapter.service';
import { NgxUploaderModule } from 'ngx-uploader'

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
    SharedModule,
    Ng2CompleterModule,
    NgxUploaderModule
  ],
  declarations: [ListaGaleriasComponent, GaleriaDetailComponent],
  providers: [GaleriaService, FileUploadService, JsonAdapterService],
  entryComponents: [ListaGaleriasComponent]
})
export class GestionGaleriasModule { }
