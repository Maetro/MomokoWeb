import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InfoAdicionalComponent } from './info-adicional.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [CommonModule, FormsModule],
  declarations: [InfoAdicionalComponent],
  exports: [InfoAdicionalComponent]
})
export class InfoAdicionalModule { }
