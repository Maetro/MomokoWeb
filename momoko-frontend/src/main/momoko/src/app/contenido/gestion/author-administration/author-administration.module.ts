import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CheckboxModule, ChipsModule, DropdownModule, FileUploadModule, GrowlModule, InputTextModule, MultiSelectModule, PickListModule, SharedModule } from 'primeng/primeng';
import { TableModule } from 'primeng/table';
import { AuthorFormComponent } from './author-form/author-form.component';
import { AuthorListComponent } from './author-list/author-list.component';
import { AuthorAdministrationRoutingModule } from './author-administration-routing.module';
import { FroalaEditorModule, FroalaViewModule } from 'angular-froala-wysiwyg';

@NgModule({
  imports: [
    CommonModule,
    InputTextModule,
    MultiSelectModule,
    GrowlModule,
    FormsModule,
    ReactiveFormsModule,
    DropdownModule,
    FileUploadModule,
    CheckboxModule,
    SharedModule,
    PickListModule,
    TableModule,
    ChipsModule,
    FroalaEditorModule.forRoot(), 
    FroalaViewModule.forRoot(),
    AuthorAdministrationRoutingModule
  ],
  declarations: [AuthorListComponent, AuthorFormComponent]
})
export class AuthorAdministrationModule { }
