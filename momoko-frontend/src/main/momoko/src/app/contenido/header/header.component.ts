import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-header',
  template: `
  <div class="row">
  <div class="col-xs-12 col-md-7">
    <div class="logo">
      <a routerLink="/">
        <img src="#" srcset="assets/style/images/logo.png 1x, assets/style/images/logo@2x.png 2x" alt="" />
      </a>
    </div>
  </div>
</div>
  `,
  styles: []
})
export class HeaderComponent implements OnInit {

  private log = environment.log;

  constructor() { }

  ngOnInit() {
  }

}
