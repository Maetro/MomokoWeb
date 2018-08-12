import { EntradaSimple } from '../../../dtos/entradaSimple';
import { Type } from '@angular/core';

export class EntradaItem {

  constructor(public component: Type<any>, public entrada: EntradaSimple) {}

}
