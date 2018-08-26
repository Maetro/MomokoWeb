
import { Libro } from '../libro';
import { Saga } from '../saga';
import { FilterRuleType } from './filter-rule-type.enum';
import { Genero } from '../genre/genero';
import { NameValue } from './name-value';

export class Filter {
  filterId: number;

  nameFilter: string;

  urlFilter: string;

  filterType: FilterRuleType;

  genres: Genero[];

  books: Libro[];

  sagas: Saga[];

  referencedProperty: string;

  possibleValues: NameValue[];

  value: string;

}
