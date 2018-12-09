import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { Angulartics2GoogleAnalytics } from 'angulartics2/ga';
import { Angulartics2Module } from 'angulartics2';
import { PROVIDERS, MODULES } from './app.imports';
import { NgtUniversalModule } from '@ng-toolkit/universal';

@NgModule({
  declarations: [AppComponent],
  imports: [
    MODULES,
    NgtUniversalModule,
    BrowserModule.withServerTransition({ appId: 'ng-universal-demo' }),
    Angulartics2Module.forRoot([Angulartics2GoogleAnalytics])
  ],
  providers: [
    PROVIDERS
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
