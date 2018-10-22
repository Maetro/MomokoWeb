import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthorListComponent } from './author-list/author-list.component';
import { InputTextModule, MultiSelectModule, GrowlModule, DropdownModule, FileUploadModule, CheckboxModule, SharedModule, PickListModule, ChipsModule } from 'primeng/primeng';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { AuthorFormComponent } from './author-form/author-form.component';

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
  declarations: [AuthorListComponent, AuthorFormComponent]
})
export class AuthorAdministrationModule { }
