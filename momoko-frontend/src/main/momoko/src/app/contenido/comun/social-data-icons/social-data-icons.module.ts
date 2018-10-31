import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SocialDataIconsComponent } from './social-data-icons.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [SocialDataIconsComponent],
  exports:[SocialDataIconsComponent]
})
export class SocialDataIconsModule { }
