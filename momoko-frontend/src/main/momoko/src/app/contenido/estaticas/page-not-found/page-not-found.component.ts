import { Component, OnInit } from '@angular/core';
import { environment } from '../../../../environments/environment';


@Component({
  selector: 'app-page-not-found',
  template: `
  <div class="light-wrapper">



  <div class="forcefullwidth_wrapper_tp_banner" id="slider1_forcefullwidth" style="position:relative;width:100%;height:auto;margin-top:0px;margin-bottom:0px">

        <img src="assets/style/images/art/Houston.jpg">

  </div>


</div>
  `,
  styles: []
})
export class PageNotFoundComponent implements OnInit {


  private log = environment.log;
  
  constructor() { }

  ngOnInit() {
  }

}
