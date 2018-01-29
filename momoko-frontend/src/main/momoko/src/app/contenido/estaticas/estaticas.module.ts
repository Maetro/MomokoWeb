import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PageSolicitudComponent } from './page-solicitud/page-solicitud.component';
import { PageCriteriosComponent } from './page-criterios/page-criterios.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [PageNotFoundComponent, PageSolicitudComponent, PageCriteriosComponent],
  exports: [PageNotFoundComponent]
})
export class EstaticasModule { }
