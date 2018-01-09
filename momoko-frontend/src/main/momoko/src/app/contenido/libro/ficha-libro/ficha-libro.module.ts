import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComunesModule } from '../../comunes/comunes.module';
import { RouterModule } from '@angular/router';
import { FichaLibroComponent } from './ficha-libro.component';
import { EntradaSimpleHorizontalComponent } from './entrada-simple-horizontal/entrada-simple-horizontal.component';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,      
    RouterModule.forChild([
      { path: '', component: FichaLibroComponent, pathMatch: 'full'}      
    ])
  ],
  declarations: [FichaLibroComponent, EntradaSimpleHorizontalComponent]
})
export class FichaLibroModule { }
