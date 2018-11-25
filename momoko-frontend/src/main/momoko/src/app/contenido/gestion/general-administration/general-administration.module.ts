import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FroalaEditorModule, FroalaViewModule } from 'angular-froala-wysiwyg';
import { CheckboxModule, ChipsModule, DropdownModule, FileUploadModule, GrowlModule, InputTextModule, MultiSelectModule, PickListModule, SharedModule } from 'primeng/primeng';
import { TableModule } from 'primeng/table';
import { GeneralAdministrationRoutingModule } from './general-administration-routing.module';
import { CustomBlockListComponent } from './custom-block-list/custom-block-list.component';
import { CustomBlockFormComponent } from './custom-block-form/custom-block-form.component';
import { GeneralAdministrationComponent } from './general-administration/general-administration.component';

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
    GeneralAdministrationRoutingModule
  ],
  declarations: [CustomBlockListComponent, CustomBlockFormComponent, GeneralAdministrationComponent]
})
export class GeneralAdministrationModule { }