import { SuscripcionService } from '../services/suscripcion.service';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { environment } from 'environments/environment';

declare var $: any;
declare var Instafeed: any;

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit, AfterViewInit {

  private log = environment.log;

  mostrarMensaje = false;

  constructor(private suscripcionService: SuscripcionService) { }

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
      after: function () {
        $('#instafeed figure.overlay a').prepend('<span class="over"><span></span></span>');
      }
    });
    $('#instafeed').each(function () {
      instagramFeed2.run();
    });
  }

  suscribirse() {
    if (this.log) {
      console.log($('.emailsuscripcion').val());
    }
    this.mostrarMensaje = true;
    const email = $('.emailsuscripcion').val().replace(/\./g, 'dot');
    this.suscripcionService.suscribirse(email).subscribe(
      () => {
        if (this.log) {
          console.log('Suscrito');
        }
      });
    setTimeout(function () {
      $('.alert-success').hide();
    }, 5000);
  }

}
