import { Component, OnInit, AfterViewInit } from '@angular/core';
import { environment } from 'environments/environment';

declare var $: any;
declare var Instafeed: any;

@Component({
  selector: 'app-sidebar-instagram',
  templateUrl: './sidebar-instagram.component.html',
  styleUrls: ['./sidebar-instagram.component.css']
})
export class SidebarInstagramComponent implements OnInit, AfterViewInit {

  private log = environment.log;

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    if (this.log) {
      console.log('Ejecutando JQuery Instagram');
    }
    const instagramFeed = new Instafeed({
      target: 'instafeed-widget',
      get: 'user',
      limit: 12,
      userId: 3260305017,
      accessToken: '3260305017.b4c416e.5f99e592a4bd49afa1b256faa597146c',
      resolution: 'low_resolution',
      clientId: 'b4c416e8ab3f424d915b5601f5d3dd88',
      // tslint:disable-next-line:max-line-length
      template: '<div class="item col-xs-4 col-sm-6 col-md-4"><figure class="overlay small"><a href="{{link}}" target="_blank"><img src="{{image}}" /></a></figure></div>',
      after: function () {
        $('#instafeed-widget figure.overlay a').prepend('<span class="over"><span></span></span>');
      }
    });
    $('#instafeed-widget').each(function () {
      instagramFeed.run();
    });

  }

}
