import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PageSolicitudComponent } from './page-solicitud/page-solicitud.component';
import { PageCriteriosComponent } from './page-criterios/page-criterios.component';
import { PageCookiesComponent } from './page-cookies/page-cookies.component';
import { PageLegalAdviceComponent } from './page-legal-advice/page-legal-advice.component';
import { PagePrivacyComponent } from './page-privacy/page-privacy.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [PageNotFoundComponent, PageSolicitudComponent, PageCriteriosComponent, PageCookiesComponent,
    PageLegalAdviceComponent, PagePrivacyComponent],
  exports: [PageNotFoundComponent]
})
export class EstaticasModule { }
