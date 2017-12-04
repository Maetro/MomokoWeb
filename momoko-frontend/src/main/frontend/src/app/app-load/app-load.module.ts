import { NgModule, APP_INITIALIZER } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { AppLoadService } from 'app/app-load/app-load.service';

export function get_settings(appLoadService: AppLoadService) {
    return () => appLoadService.getDatosGenerales();
}

@NgModule({
  imports: [HttpClientModule],
  providers: [
    AppLoadService,
    { provide: APP_INITIALIZER, useFactory: get_settings, deps: [AppLoadService], multi: true }
  ]
})
export class AppLoadModule { }
