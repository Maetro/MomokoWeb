import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer.component';
import { LazyLoadModule } from '../lazy-load/lazy-load.module';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    LazyLoadModule
  ],
  declarations: [FooterComponent],
  exports:[FooterComponent]
})
export class FooterModule { }
