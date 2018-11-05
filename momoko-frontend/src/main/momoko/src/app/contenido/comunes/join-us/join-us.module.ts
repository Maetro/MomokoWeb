import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JoinUsComponent } from './join-us.component';
import { JoinUsAuthorComponent } from './join-us-author/join-us-author.component';
import { JoinUsAuthorFormComponent } from './join-us-author-form/join-us-author-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { JoinUsEditorComponent } from './join-us-editor/join-us-editor.component';
import { JoinUsPublisherComponent } from './join-us-publisher/join-us-publisher.component';
import { JoinUsEditorFormComponent } from './join-us-editor-form/join-us-editor-form.component';
import { JoinUsPublisherFormComponent } from './join-us-publisher-form/join-us-publisher-form.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  declarations: [JoinUsComponent, JoinUsAuthorComponent, JoinUsAuthorFormComponent,
    JoinUsEditorComponent, JoinUsPublisherComponent, JoinUsEditorFormComponent, 
    JoinUsPublisherFormComponent],
  exports: [JoinUsComponent]
})
export class JoinUsModule { }
