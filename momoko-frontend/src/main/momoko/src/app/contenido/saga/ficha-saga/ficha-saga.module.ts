import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComunesModule } from '../../comunes/comunes.module';
import { RouterModule } from '@angular/router';
import { FichaSagaComponent } from './ficha-saga.component';

@NgModule({
  imports: [
    CommonModule,
    ComunesModule,      
    RouterModule.forChild([
      { path: '', component: FichaSagaComponent, pathMatch: 'full'}      
    ])
  ],
  declarations: [FichaSagaComponent]
})
export class FichaSagaModule { }
