import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputTextModule, MultiSelectModule } from 'primeng/primeng';
import { ListaEntradasComponent } from 'app/contenido/gestion-entradas/lista-entradas/lista-entradas.component';
import { FileUploadService } from 'app/services/fileUpload.service';
import { EntradaService } from 'app/services/entrada.service';
import { EntradaDetailComponent } from 'app/contenido/gestion-entradas/entrada-detail/entrada-detail.component';

@NgModule({
  imports: [
    CommonModule,
    CommonModule,
    InputTextModule,
    MultiSelectModule,
  ],
  declarations: [ListaEntradasComponent, EntradaDetailComponent ],
  providers: [
    EntradaService, FileUploadService
  ],
  entryComponents: [ListaEntradasComponent]
})
export class GestionEntradasModule { }
