import { Component, OnInit, AfterViewInit } from '@angular/core';

declare var $: any;
declare var Instafeed: any;

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit, AfterViewInit {

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    const instagramFeed2 = new Instafeed({
      target: 'instafeed',
      get: 'user',
      limit: 8,
      userId: 3260305017,
      accessToken: '3260305017.b4c416e.5f99e592a4bd49afa1b256faa597146c',
      resolution: 'low_resolution',
      clientId: 'b4c416e8ab3f424d915b5601f5d3dd88',
      // tslint:disable-next-line:max-line-length
      template: '<div class="item col-8"><figure class="overlay instagram"><a href="{{link}}" target="_blank"><img src="{{image}}" /></a></figure></div>',
      after: function() {
          $('#instafeed figure.overlay a').prepend('<span class="over"><span></span></span>');
      }
  });
  $('#instafeed').each(function() {
      instagramFeed2.run();
  });
  }

}
