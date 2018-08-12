import { Genero } from '../genero';
import { Libro } from '../libro';
import { Saga } from '../saga';
import { FilterRuleType } from './filter-rule-type.enum';

export class Filter {
  filterId: number;

  nameFilter: string;

  urlFilter: string;

  filterType: FilterRuleType;

  genres: Genero[];

  books: Libro[];

  sagas: Saga[];

  referencedProperty: string;

  possibleValues: string[];

}
