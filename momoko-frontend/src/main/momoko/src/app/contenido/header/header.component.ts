import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-header',
  template: `
  <div class="row">
  <div class="col-xs-12 col-md-12">
    <div class="logo">
      <a routerLink="/">
        <img src="https://momoko.es/images/logo_momoko.png" alt="Logotipo de momoko.es" />
      </a>
    </div>
  </div>
</div>
  `,
  styles: ['.logo {margin: 115px auto 50px; text-align:center;} ']
})
export class HeaderComponent implements OnInit {
  private log = environment.log;

  constructor() {}

  ngOnInit() {}
}
