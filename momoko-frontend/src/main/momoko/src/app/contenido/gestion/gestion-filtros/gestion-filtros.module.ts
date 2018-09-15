import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
// tslint:disable-next-line:max-line-length
import {
  GrowlModule,
  SharedModule,
  CheckboxModule,
  FileUploadModule,
  InputTextModule,
  PickListModule
} from 'primeng/primeng';
import { TableModule } from 'primeng/table';
import { DropdownModule } from 'primeng/dropdown';
import { FilterListComponent } from './filter-list/filter-list.component';
import { FilterService } from './filter.service';
import { ChipsModule } from 'primeng/chips';
import { MultiSelectModule } from 'primeng/multiselect';
import { FilterFormComponent } from './filter-form/filter-form.component';
@NgModule({
  imports: [
    CommonModule,
    InputTextModule,
    MultiSelectModule,
    GrowlModule,
    FormsModule,
    DropdownModule,
    FileUploadModule,
    CheckboxModule,
    SharedModule,
    PickListModule,
    TableModule,
    ChipsModule
  ],
  declarations: [
    FilterListComponent,
    FilterFormComponent
  ],
  providers: [FilterService]
})
export class FilterAdministrationModule {}