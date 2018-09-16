import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { LibroSimple } from '../../../dtos/libroSimple';
import { Router } from '@angular/router';
import { EntradaSimple } from '../../../dtos/entradaSimple';
import { Filter } from '../../../dtos/filter/filter';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  private log = environment.log;

  busqueda = '';

  @Input()
  libros: LibroSimple[];

  @Input()
  tituloSeccionLibros: string;

  @Input()
  entradas: EntradaSimple[];

  @Input()
  tituloSeccionEntradas: string;

  _filters: Filter[];

  @Input()
  set filters(value: Filter[]) {
    this.basicFilters = [];
    this.advancedFilters = [];
    if (value && value.length > 0) {
      value.forEach(element => {
        if (element.basic) {
          this.basicFilters.push(element);
        } else {
          this.advancedFilters.push(element);
        }
      });
    }
  }

  basicFilters: Filter[];

  advancedFilters: Filter[];

  @Output()
  onUpdateFilters: EventEmitter<Filter[]> = new EventEmitter<Filter[]>();

  selectedValues: string[] = [];

  constructor(private router: Router) {}

  ngOnInit() {}

  buscarResultados() {
    if (this.log) {
      console.log('Buscar: ' + this.busqueda);
    }
    this.router.navigate(['/buscar/' + this.busqueda]);
    this.busqueda = '';
  }

  filterSearch() {
    console.log('filterSearch');
  }

  updateFilters() {
    this._filters = [];
    console.log('update Filters');
    this.basicFilters.forEach(element => {
      this._filters.push(element);
    });
    this.advancedFilters.forEach(advancedFilter => {
      advancedFilter.selectedFilterValues = [];
      if (advancedFilter.stringSelectedValues && advancedFilter.stringSelectedValues.length > 0){
        advancedFilter.stringSelectedValues.forEach(stringValue => {
          advancedFilter.filterValues.forEach(possibleValue => {
            if (possibleValue.value === stringValue){
              advancedFilter.selectedFilterValues.push(possibleValue.filterValueId);
            }
          });
        });
      } 
      this._filters.push(advancedFilter);
    });
    this.onUpdateFilters.emit(this._filters);
  }
}
