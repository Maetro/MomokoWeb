import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RedactorInfoComponent } from './redactor-info.component';
import { RouterModule } from '@angular/router';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { SocialDataIconsModule } from '../social-data-icons/social-data-icons.module';

@NgModule({
  imports: [
    CommonModule,
    AngularFontAwesomeModule,
    SocialDataIconsModule,
    RouterModule
  ],
  declarations: [RedactorInfoComponent],
  exports: [
    RedactorInfoComponent
  ]
})
export class RedactorInfoModule { }
