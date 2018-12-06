import {
  Component,
  OnInit,
  AfterViewInit,
  Inject,
  PLATFORM_ID
} from '@angular/core';
import { environment } from '../../../environments/environment';
import { isPlatformBrowser } from '@angular/common';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { SuscribeService } from './suscribe.service';
import { SuscribeContactRequest } from './suscribe-contact-request';

declare var $: any;
declare var Instafeed: any;

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit, AfterViewInit {
  submitted = false;

  private log = environment.log;

  suscribeRequestForm: FormGroup;

  showInstaFeed = false;
  showSuscribe = false;

  constructor(
    private suscribeService: SuscribeService,
    @Inject(PLATFORM_ID) private platformId: Object,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit() {
    this.getSuscribeRequestForm();
  }

  ngAfterViewInit(): void {}

  suscribirse() {
    if (this.log) {
      console.log($('.emailsuscripcion').val());
    }
    const email = $('.emailsuscripcion')
      .val()
      .replace(/\./g, 'dot');
    this.suscribeService.sendEmailSuscribe(email).subscribe(() => {
      if (this.log) {
        console.log('Suscrito');
      }
    });
    setTimeout(function() {
      $('.alert-success').hide();
    }, 5000);
  }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.suscribeRequestForm.invalid) {
      let detail = '';

      if (this.suscribeF.email.errors.required) {
        detail += 'El email es obligatorio';
      } else if (this.suscribeF.email.errors.email) {
        detail += 'El email no tiene un formato correcto';
      }
      if (isPlatformBrowser(this.platformId)) {
        $.growl.error({ message: '' + detail });
      }
      return;
    }
    this.suscribeRequestForm.controls;
    const suscribeContactRequest: SuscribeContactRequest = this.suscribeRequestForm.getRawValue();
    this.suscribeService.sendEmailSuscribe(suscribeContactRequest).subscribe(
      response => {
        if (isPlatformBrowser(this.platformId)) {
          if (response === 'SEND') {
            $.growl.notice({
              title: 'Mensaje enviado',
              message:
                'Se ha enviado el mensaje correctamente, te responderemos lo antes posible.'
            });
            this.suscribeRequestForm.reset();
            this.suscribeRequestForm.markAsPristine();
            this.suscribeRequestForm.markAsUntouched();
          } else {
            $.growl.error({
              title: 'Error',
              message:
                'Ha ocurrido un error al suscribirte. Intentalo de nuevo más adelante'
            });
          }
        }
      },
      error => {
        if (isPlatformBrowser(this.platformId)) {
          $.growl.error({
            title: 'Error',
            message:
              'Ha ocurrido un error al suscribirte. Intentalo de nuevo más adelante'
          });
        }
      }
    );
  }

  get suscribeF() {
    return this.suscribeRequestForm.controls;
  }

  private getSuscribeRequestForm() {
    this.suscribeRequestForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  downloadInstaFeed() {
    this.showInstaFeed = true;
    console.log('downloadInstaFeed');
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
        template:
          '<div class="item col-8"><figure class="overlay instagram"><a href="{{link}}" target="_blank"><img src="{{image}}" alt="Imagenes instagram de momoko"/></a></figure></div>',
        after: function() {
          $('#instafeed figure.overlay a').prepend(
            '<span class="over"><span></span></span>'
          );
        }
      });

      instagramFeed2.run();
    }
  }
}
