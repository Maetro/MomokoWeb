/* 3rd party libraries */
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material';
/* our own custom components */
import { SomeCustomComponent } from './some-custom/some-custom.component';
import { HeaderMenuComponent } from './header-menu/header-menu.component';


@NgModule({
  imports: [
    /* angular stuff */
    CommonModule,
    FormsModule,

    /* 3rd party components */
    MatButtonModule,
  ],
  declarations: [
    SomeCustomComponent,
    HeaderMenuComponent
  ],
  exports: [
    /* angular stuff */
    CommonModule,
    FormsModule,

    /* 3rd party components */
    MatButtonModule,

    /* our own custom components */
    SomeCustomComponent
  ]
})
export class SharedModule { }
