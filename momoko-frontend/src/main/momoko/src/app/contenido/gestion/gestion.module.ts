import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GestionRoutingModule } from './gestion-routing.module';
import { GestionComponent } from './gestion.component';
import { GestionService } from './gestion.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    GestionRoutingModule
  ],
  declarations: [GestionComponent],
  providers:[GestionService]
})
export class GestionModule { }
