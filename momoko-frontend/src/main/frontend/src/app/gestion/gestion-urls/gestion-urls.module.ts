import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaUrlsComponent } from './lista-urls/lista-urls.component';
import { ModificadorUrlsComponent } from './modificador-urls/modificador-urls.component';
import { DataTableModule } from 'primeng/primeng';
import { HerramientasService } from '../../services/herramientas.service';

@NgModule({
  imports: [CommonModule, DataTableModule],
  declarations: [ListaUrlsComponent, ModificadorUrlsComponent],
  providers: [HerramientasService]
})
export class GestionUrlsModule {}
