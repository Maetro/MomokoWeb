import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaGaleriasComponent } from 'app/gestion/gestion-galerias/lista-galerias/lista-galerias.component';
import { GaleriaDetailComponent } from 'app/gestion/gestion-galerias/galeria-detail/galeria-detail.component';
// tslint:disable-next-line:max-line-length
import { InputTextModule, MultiSelectModule, GrowlModule, DropdownModule, FileUploadModule, DataTableModule, SharedModule } from 'primeng/primeng';
import { FormsModule } from '@angular/forms';
import { Ng2CompleterModule } from 'ng2-completer';
import { FileUploadService } from 'app/services/fileUpload.service';
import { JsonAdapterService } from 'app/util/json-adapter.service';
import { GaleriaService } from 'app/services/galeria.service';
import { NgUploaderModule } from 'ngx-uploader';

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
    NgUploaderModule
  ],
  declarations: [ListaGaleriasComponent, GaleriaDetailComponent],
  providers: [GaleriaService, FileUploadService, JsonAdapterService],
  entryComponents: [ListaGaleriasComponent]
})
export class GestionGaleriasModule { }
