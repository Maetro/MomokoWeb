import { Type } from '@angular/core';
import { EntradaSimple } from '../../../dtos/entradaSimple';

export class EntradaItem {

  constructor(public component: Type<any>, public entrada: EntradaSimple) {}

}