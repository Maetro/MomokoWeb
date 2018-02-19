import { Component, OnInit, Input } from '@angular/core';
import { EntradaPortada } from '../entrada-portada.model';
import { environment } from '../../../../../environments/environment';
import { SafeUrl, DomSanitizer } from '@angular/platform-browser';
import { EntradaSimple } from '../../../../dtos/entradaSimple';
import { UtilService } from '../../../../services/util/util.service';

@Component({
  selector: 'app-entrada-portada-video',
  templateUrl: './entrada-portada-video.component.html',
  styleUrls: ['./entrada-portada-video.component.css']
})
export class EntradaPortadaVideoComponent implements OnInit, EntradaPortada {


  private log = environment.log;

  safeURL: SafeUrl;

  @Input() data: EntradaSimple;

  constructor(
    private domSanitizationService: DomSanitizer,
    private util: UtilService
  ) {}

  ngOnInit(): void {
    if (this.log){
      console.log('Creando portada video');
    }
    const partes = this.data.videoUrl.split('/');
    const url = 'https://www.youtube.com/embed/' + partes[partes.length - 1];
    this.safeURL = this.domSanitizationService.bypassSecurityTrustResourceUrl(url);

  }

  obtenerUrlEntradaSimple(entrada: EntradaSimple): string{
    return this.util.obtenerUrlEntradaSimple(entrada);
  }

}
