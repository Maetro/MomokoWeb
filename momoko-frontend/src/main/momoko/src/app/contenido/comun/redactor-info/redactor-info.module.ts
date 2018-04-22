import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RedactorInfoComponent } from './redactor-info.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    RouterModule
  ],
  declarations: [RedactorInfoComponent],
  exports: [
    RedactorInfoComponent
  ]
})
export class RedactorInfoModule { }
