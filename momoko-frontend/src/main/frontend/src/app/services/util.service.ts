import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';

@Injectable()
export class UtilService {

  private log = environment.log;

  constructor() { }

  convertToSlug(text: string) {
    text = text.trim();
    const from = 'ãàáäâẽèéëêìíïîõòóöôùúüûñç·/_,:;';
    const to = 'aaaaaeeeeeiiiiooooouuuunc------';
    for (let i = 0, l = from.length; i < l; i++) {
      text = text.replace(new RegExp(from.charAt(i), 'g'), to.charAt(i));
    }

    return text
      .toLowerCase()
      .replace(/[^\w ]+/g, '')
      .replace(/ +/g, '-')
      ;
  }
}
