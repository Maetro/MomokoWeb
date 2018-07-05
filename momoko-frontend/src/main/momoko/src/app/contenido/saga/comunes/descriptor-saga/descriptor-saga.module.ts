import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DescriptorSagaComponent } from './descriptor-saga.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
  ],
  declarations: [DescriptorSagaComponent],
  exports: [DescriptorSagaComponent]
  
})
export class DescriptorSagaModule { }
