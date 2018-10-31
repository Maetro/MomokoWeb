import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { PaginationModule } from '../pagination/pagination.module';
import { AuthorPageRoutingModule } from './author-page-routing.module';
import { AuthorPageComponent } from './author-page.component';
import { SocialDataIconsModule } from 'app/contenido/comun/social-data-icons/social-data-icons.module';


@NgModule({
  imports: [
    CommonModule,
    PaginationModule,
    SocialDataIconsModule,
    AuthorPageRoutingModule
  ],
  declarations: [AuthorPageComponent]
})
export class AuthorPageModule { }