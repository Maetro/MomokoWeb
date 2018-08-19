import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GestionRoutingModule } from './gestion-routing.module';
import { GestionComponent } from './gestion.component';
import { GestionService } from './gestion.service';
import { FilterAdministrationModule } from './gestion-filtros/gestion-filtros.module';
import { GestionEntradasModule } from './gestion-entradas/gestion-entradas.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    FilterAdministrationModule,
    GestionEntradasModule,
    GestionRoutingModule,
  ],
  declarations: [GestionComponent],
  providers:[GestionService]
})
export class GestionModule { }
