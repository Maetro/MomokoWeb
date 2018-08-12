import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
// tslint:disable-next-line:max-line-length
import {
  DataTableModule,
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
import { CreateFilterComponent } from './create-filter/create-filter.component';
import { EditFilterComponent } from './edit-filter/edit-filter.component';
import { ChipsModule } from 'primeng/chips';
import { MultiSelectModule } from 'primeng/multiselect';

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
    TableModule,
    ChipsModule
  ],
  declarations: [
    FilterListComponent,
    CreateFilterComponent,
    EditFilterComponent
  ],
  providers: [FilterService]
})
export class FilterAdministrationModule {}
