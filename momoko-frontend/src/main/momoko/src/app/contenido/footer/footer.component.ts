
import { Component, OnInit, AfterViewInit, Inject, PLATFORM_ID } from '@angular/core';
import { environment } from '../../../environments/environment';
import { SuscripcionService } from '../../services/suscripcion.service';
import { isPlatformBrowser } from '@angular/common';

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

  constructor(private suscripcionService: SuscripcionService, @Inject(PLATFORM_ID) private platformId: Object) { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      // Client only code.
      const instagramFeed2 = new Instafeed({
        target: 'instafeed',
        get: 'user',
        limit: 8,
        userId: 3260305017,
        accessToken: '3260305017.b4c416e.b1061ec74477419fa0f8e7d66bc285de',
        resolution: 'low_resolution',
        clientId: 'b4c416e8ab3f424d915b5601f5d3dd88',
        // tslint:disable-next-line:max-line-length
        template: '<div class="item col-8"><figure class="overlay instagram"><a href="{{link}}" target="_blank"><img src="{{image}}" alt="Imagenes instagram de momoko"/></a></figure></div>',
        after: function () {
          $('#instafeed figure.overlay a').prepend('<span class="over"><span></span></span>');
        }
      });
      $('#instafeed').each(function () {
        instagramFeed2.run();
      });
    }

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
