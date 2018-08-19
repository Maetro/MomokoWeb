import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RedactorInfoComponent } from './redactor-info.component';
import { RouterModule } from '@angular/router';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

@NgModule({
  imports: [
    CommonModule,
    AngularFontAwesomeModule,
    RouterModule
  ],
  declarations: [RedactorInfoComponent],
  exports: [
    RedactorInfoComponent
  ]
})
export class RedactorInfoModule { }
