import { SimpleTinyComponent } from './simple-tiny/simple-tiny.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputTextModule, MultiSelectModule, GrowlModule } from 'primeng/primeng';
import { ListaEntradasComponent } from 'app/contenido/gestion-entradas/lista-entradas/lista-entradas.component';
import { FileUploadService } from 'app/services/fileUpload.service';
import { EntradaService } from 'app/services/entrada.service';
import { EntradaDetailComponent } from 'app/contenido/gestion-entradas/entrada-detail/entrada-detail.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  imports: [
    CommonModule,
    CommonModule,
    InputTextModule,
    FormsModule,
    GrowlModule,
    MultiSelectModule,
  ],
  declarations: [ListaEntradasComponent, EntradaDetailComponent, SimpleTinyComponent ],
  providers: [
    EntradaService, FileUploadService
  ],
  entryComponents: [ListaEntradasComponent]
})
export class GestionEntradasModule { }
