import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LivetestComponent } from './livetest.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: '', component: LivetestComponent, pathMatch: 'full'}
    ])
  ],
  declarations: [LivetestComponent]
})
export class LivetestModule { }
