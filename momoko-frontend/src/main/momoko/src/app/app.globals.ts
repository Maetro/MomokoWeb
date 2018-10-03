import { Injectable } from '@angular/core';
import { OrderType } from './dtos/enums/ordertype';

@Injectable()
export class Globals {
  orderType: OrderType = OrderType.DATE;
  bookfilter: string;
  entryfilter: string;
  loading: boolean;
}