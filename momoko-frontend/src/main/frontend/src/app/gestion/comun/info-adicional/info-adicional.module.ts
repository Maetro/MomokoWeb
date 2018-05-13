import { InfoAdicionalComponent } from './info-adicional.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [CommonModule, FormsModule],
  declarations: [InfoAdicionalComponent],
  exports: [InfoAdicionalComponent]
})
export class InfoAdicionalModule {}
