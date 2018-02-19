import { Injectable } from '@angular/core';
import { EntradaSimple } from '../../dtos/entradaSimple';
import { Meta } from '@angular/platform-browser';

@Injectable()
export class UtilService {


  constructor() { }

  obtenerUrlEntradaSimple(entrada: EntradaSimple): string{
    let urlEntrada = '';
    if (entrada.urlLibro != null){
      urlEntrada = 'libro/' + entrada.urlLibro + '/' + entrada.bloque + '/' + entrada.urlEntrada;
    } else {
      urlEntrada = entrada.urlEntrada;
    }
    return urlEntrada;

  }

  removeAllTags(metaService: Meta): any {
    metaService.removeTag('name="description"');
    metaService.removeTag('name="og:type"');
    metaService.removeTag('name="og:title"');
    metaService.removeTag('name="og:description"');
    metaService.removeTag('name="og:image"');
    metaService.removeTag('name="og:url"');
    metaService.removeTag('name="og:locale"');
    metaService.removeTag('name="fb:app_id"');
    metaService.removeTag('rel="canonical"');
  }

}
