import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { PaginationModule } from '../pagination/pagination.module';
import { AuthorPageRoutingModule } from './author-page-routing.module';
import { AuthorPageComponent } from './author-page.component';


@NgModule({
  imports: [
    CommonModule,
    PaginationModule,
    AuthorPageRoutingModule
  ],
  declarations: [AuthorPageComponent]
})
export class AuthorPageModule { }