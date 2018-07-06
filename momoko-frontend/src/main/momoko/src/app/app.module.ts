import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { Angulartics2GoogleAnalytics } from 'angulartics2/ga';
import { Angulartics2Module } from 'angulartics2';
import { PROVIDERS, MODULES } from './app.imports';

@NgModule({
  declarations: [AppComponent],
  imports: [
    MODULES,
    BrowserModule.withServerTransition({ appId: 'momoko-app' }),
    Angulartics2Module.forRoot([Angulartics2GoogleAnalytics])
  ],
  providers: [
    PROVIDERS
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
