import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GestionRoutingModule } from './gestion-routing.module';
import { GestionComponent } from './gestion.component';
import { GestionService } from './gestion.service';
import { FilterAdministrationModule } from './gestion-filtros/gestion-filtros.module';
import { GestionEntradasModule } from './gestion-entradas/gestion-entradas.module';
import { GestionLibrosModule } from './gestion-libros/gestion-libros.module';
import { GestionGenerosModule } from './gestion-generos/gestion-generos.module';
import { GestionSagasModule } from './gestion-sagas/gestion-sagas.module';
import { GestionGaleriasModule } from './gestion-galerias/gestion-galerias.module';
import { GestionRedactoresModule } from './gestion-redactores/gestion-redactores.module';
import { GestionEditorialesModule } from './gestion-editoriales/gestion-editoriales.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    FilterAdministrationModule,
    GestionEntradasModule,
    GestionLibrosModule,
    GestionGenerosModule,
    GestionSagasModule,
    GestionGaleriasModule,
    GestionRedactoresModule,
    GestionEditorialesModule,
    GestionRoutingModule,
  ],
  declarations: [GestionComponent],
  providers:[GestionService]
})
export class GestionModule { }
