import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Ng2CompleterModule } from 'ng2-completer';
import { CheckboxModule } from 'primeng/components/checkbox/checkbox';
import { PickListModule } from 'primeng/components/picklist/picklist';
// tslint:disable-next-line:max-line-length
import { DataTableModule, DropdownModule, FileUploadModule, GrowlModule, InputTextModule, MultiSelectModule, SharedModule } from 'primeng/primeng';
import { RedactorService } from '../../services/redactor.service';
import { ListaRedactoresComponent } from './lista-redactores/lista-redactores.component';
import { RedactorDetailComponent } from './redactor-detail/redactor-detail.component';


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
    Ng2CompleterModule
  ],
  declarations: [ListaRedactoresComponent, RedactorDetailComponent],
  providers: [RedactorService]
})
export class GestionRedactoresModule {}
