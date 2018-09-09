import { Injectable } from '@angular/core';
import { EntradaSimple } from '../../dtos/entradaSimple';
import { Meta } from '@angular/platform-browser';
import { Cookie } from 'ng2-cookies';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class UtilService {
  
  constructor() {}

  obtenerUrlEntradaSimple(entrada: EntradaSimple): string {
    let urlEntrada = '';
    if (entrada.urlLibro != null) {
      urlEntrada = entrada.bloque + '/' + entrada.urlEntrada;
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

  convertToSlug(text: string) {
    text = text.trim();
    const from = 'ãàáäâẽèéëêìíïîõòóöôùúüûñç·/,:;';
    const to = 'aaaaaeeeeeiiiiooooouuuunc-----';
    for (let i = 0, l = from.length; i < l; i++) {
      text = text.replace(new RegExp(from.charAt(i), 'g'), to.charAt(i));
    }
    return text
      .toString()
      .toLowerCase()
      .replace(/\s+/g, '-') // Replace spaces with -
      .replace(/[^\w\-]+/g, '') // Remove all non-word chars
      .replace(/\-\-+/g, '-') // Replace multiple - with single -
      .replace(/^-+/, '') // Trim - from start of text
      .replace(/-+$/, '');
  }

  getHeaderWithAuth(): HttpHeaders {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: Cookie.get('access_token')
    });
    return headers;
  }
}
